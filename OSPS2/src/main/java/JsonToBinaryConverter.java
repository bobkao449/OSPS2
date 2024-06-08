import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToBinaryConverter {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // 读取JSON文件
            File jsonFile = new File("C:\\saproject1\\OSPS2\\OSPS2\\src\\main\\resources\\static\\parkingspace.json");
            FileInputStream fileInputStream = new FileInputStream(jsonFile);
            byte[] jsonData = new byte[(int) jsonFile.length()];
            fileInputStream.read(jsonData);
            fileInputStream.close();

            // 将JSON转换为Java对象
            Object jsonObject = mapper.readValue(jsonData, Object.class);

            // 将Java对象转换为JSON格式的字节数组
            byte[] binaryData = mapper.writeValueAsBytes(jsonObject);

            // 将字节数组写入文件
            FileOutputStream fileOutputStream = new FileOutputStream("output.bin");
            fileOutputStream.write(binaryData);
            fileOutputStream.close();

            System.out.println("JSON转换为二进制成功！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
