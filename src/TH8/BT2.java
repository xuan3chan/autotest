package TH8;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BT2 {

  public static void main(String[] args) throws Exception {
    // Replace "path/to/your/barcode.png" with the actual path to your image
    BufferedImage image = ImageIO.read(new File("C:\\Users\\xuann\\Desktop\\videoposet\\barcode_newa.png"));
    LuminanceSource source = new BufferedImageLuminanceSource(image);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
    Result result = new MultiFormatReader().decode(bitmap);
    System.out.println("Barcode text: " + result.getText());
    
  }
}
