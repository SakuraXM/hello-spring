package common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0
 * @description 二维码生成工具类
 * @date 2022/03/11
 */
public class QrCodeUtil {

    private QrCodeUtil() {
    }

    /**
     * 默认二维码宽度
     */
    private static final int DEFAULT_WIDTH = 200;
    /**
     * 默认二维码高度
     */
    private static final int DEFAULT_HEIGHT = 200;
    /**
     * 默认二维码文件格式
     */
    private static final String FORMAT = "png";
    /**
     * 二维码参数
     */
    private static final Map<EncodeHintType, Object> HINTS = new HashMap(4);

    static {
        // 字符编码
        HINTS.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码与图片边距
        HINTS.put(EncodeHintType.MARGIN, 2);
    }

    /**
     * @param content 二维码内容
     * @description 返回一个 BufferedImage 对象
     */
    public static BufferedImage toBufferedImage(String content) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, HINTS);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * @param content 二维码内容
     * @param stream  输出流
     * @description 将二维码图片输出到一个流中
     */
    public static void writeToStream(String content, OutputStream stream) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, HINTS);
        MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, stream);
    }

    /**
     * @param content 二维码内容
     * @param path    文件保存路径
     * @description 生成二维码图片文件
     */
    public static void createQrCode(String content, String path) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, HINTS);
        // toPath() 方法由 jdk1.7 及以上提供
        MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, new File(path).toPath());
    }

    public static BitMatrix createCode(String content) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, HINTS);
        return deleteWhite(bitMatrix);
    }

    /**
     * @param bitMatrix 二维码内容
     * @description 删除二维码白边
     */
    private static BitMatrix deleteWhite(BitMatrix bitMatrix) {
        int[] rec = bitMatrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        BitMatrix matrix = new BitMatrix(resWidth, resHeight);
        matrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (bitMatrix.get(i + rec[0], j + rec[1])) {
                    matrix.set(i, j);
                }
            }
        }
        return matrix;
    }

}
