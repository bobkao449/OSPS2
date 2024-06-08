import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImageFolderToDatabase {

    public static void main(String[] args) {
        String folderPath = "C:\\saproject1\\OSPS2\\OSPS2\\src\\main\\resources\\static\\images"; // 图像文件夹的路径

        // 获取文件夹中的所有图像文件路径
        try {
            List<Path> imagePaths = Files.list(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        String mimeType;
                        try {
                            mimeType = Files.probeContentType(path);
                        } catch (IOException e) {
                            return false;
                        }
                        return mimeType != null && mimeType.startsWith("image");
                    })
                    .toList();

            // 将每个图像文件保存到数据库
            for (Path imagePath : imagePaths) {
                try {
                    saveImageToDatabase(imagePath);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveImageToDatabase(Path imagePath) throws IOException, SQLException {
        // 读取图像文件并将其转换为字节数组
        byte[] imageData = Files.readAllBytes(imagePath);

        // 将字节数组存储到数据库中
        String dbUrl = "jdbc:mysql://localhost:3306/osps";
        String username = "root";
        String password = "bobkaott12345";

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            String sql = "INSERT INTO picture (name, data) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, imagePath.getFileName().toString());
                statement.setBytes(2, imageData);
                statement.executeUpdate();
                System.out.println("Image " + imagePath.getFileName() + " saved to database successfully!");
            }
        }
    }
}
