package com.example.shems.util;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

import com.example.smarthome.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import net.wimpi.modbus.util.ModbusUtil;

/**
 * @author Wen Tong Che
 *
 */
public class CommonFunction {

   static public byte[] calculateCRC(byte[]data,int offset,int len){
        int []ints=ModbusUtil.calculateCRC(data,offset,len);
        byte[]bytes=new byte[2];
        bytes[0]= (byte) ints[0];
        bytes[1]= (byte) ints[1];
        return  bytes;
    }
    /**
     * @param bytesList byte[]数组
     * @return 合并后的数组
     */
    public static byte[] bytesMerger(byte[]...bytesList){
        int size=0;
        for(byte[] srcBytes :bytesList){
            size+= srcBytes.length;
        }
        byte[]retBytes=new byte[size];
        int start=0;
        for(byte[] srcBytes :bytesList){
            System.arraycopy(srcBytes, 0, retBytes, start, srcBytes.length);
            start+=srcBytes.length;
        }
        return retBytes;
    }

    /**
     * @param floatVal
     * @return  保留2位小数的浮点数字符串
     */
    public static String floatWith2Bit(float floatVal){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        return decimalFormat.format(floatVal);
    }

    public static String doubleWith2Bit(double doubleVal){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        return decimalFormat.format(doubleVal);
    }
    /**
     * 如果srcNum是byte，要先和0x00ff与一下再传进来！
     *
     * @param bitNum
     * @param srcNum
     * @param sepChar
     * @return 返回字符串为 00 01 0x， 分隔符由sepChar确定，如果为空，请用""，bitNum是字节数
     */
    public static String changeIntegerToHexStr(int bitNum, int srcNum,
                                               String sepChar) {
        String result = "";
        while (srcNum != 0) {
            result = CommonFunction.changeDecimalTo2BitHexStr(srcNum % 256)
                    + sepChar + result;
            srcNum = srcNum / 256;
        }
        int len = result.length();
        int totalLen = bitNum * (2 + sepChar.length());
        while (len < totalLen) {
            result = "00" + sepChar + result;
            len = len + 2 + sepChar.length();
        }
        return result;
    }

    // used by changeIntegerToHexStr
    public static String changeDecimalTo2BitHexStr(int decimal) {
        String result = "";
        char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f' };
        int tempInt = (decimal & 0x0f);
        result = hex[tempInt] + result;
        tempInt = (decimal >> 4) & 0x0f;
        result = hex[tempInt] + result;
		/*
		 * if(decimal <0 || decimal >255){
		 * System.out.println("change: "+decimal); return ""; } int remain =
		 * 0;//余数 String result = ""; char[] hex =
		 * {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		 * while(true){ remain = decimal%16; decimal = decimal/16; result =
		 * hex[remain] + result; if(decimal == 0){ break; } } if(result.length()
		 * == 1){ result = 0+result; }else if(result.length() == 0){ result =
		 * "00"; }
		 */
        return result;
    }

    /**
     *
     * @param title
     * @param message
     */
    public static void showDialog(String title, String message,
                                  Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", null);
        builder.create().show();
    }

    public static void showDialogWithinMaxSize(String title, String message,
                                               Activity activity ) {
        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        ab.setTitle(title);
        ab.setMessage(message);
        AlertDialog dlg = ab.create();
        //dlg.setCanceledOnTouchOutside(true);// 设置dialog外面点击则消失
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
        window.setContentView(R.layout.xalertdlg);
        window.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView txtView=(TextView) window.findViewById(R.id.xalert_txt);
        // Button button=(Button)window.findViewById(R.id.btn_formore);

//		 button.setOnClickListener(new Button(context) {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent();
//				intent.setClass(context, ACScheduleDetailChart.class);
//				context.startActivity(intent);
//			}
//		});
        txtView.setText(message);

        //dlg.getWindow().setContentView(R.layout.alert_dialog_border);
    }

    /**
     * change the byte array to ascii String
     *
     * @param b
     *            the byte Array
     * @param len
     *            the length
     * @return
     */
    public static String getASCStr(byte[] b, int len) {
        char c;
        String result = "";
        for (int i = 0; i < len; i++) {
            c = (char) b[i];
            result = result + c;
            // System.out.print(c+" ");
        }
        return result;

    }

    /**
     *
     * @param hex
     * @return
     */
    public static int changeHexStrTobyte(String hex) {
        char first = hex.charAt(0);
        char second = hex.charAt(1);
        int result = 0;
        if (first >= '0' && first <= '9') {
            result = (result + (first - '0'));
        } else {
            result = (result + (first - 'a') + 10);
        }
        if (second >= '0' && second <= '9') {
            result = (result * 16 + (second - '0'));
        } else {
            result = (result * 16 + (second - 'a') + 10);
        }
        return result;
    }

    /**
     * 注意hex的长度必须是偶数而且不包含其他字符
     *
     * @param hex
     * @return
     */
    public static int changeHexStrToInteger(String hex) {
        int len = hex.length();
        int result = 0;
        for (int i = 0; i < len;) {
            result = result * 256 + changeHexStrTobyte(hex.substring(0, 2));
            hex = hex.substring(2);
            i = i + 2;
        }
        return result;

    }

    /**
     * author:cyl 只是被调用 不要直接使用 //hexbytesnum==1 得到0x000000ff
     */
    public static int getHex(int hexbytesnum) {
        int retInt = 0xffffffff;
        int changebit = (4 - hexbytesnum) * 8;
        if (changebit > 0) {
            retInt = retInt >> changebit;
        }
        return retInt;
    }

    /**
     * author:cyl
     *
     * @param bytes
     *            (byte[])
     * @return float(float) function :从底层byte[]解析出float类型的数 assert:4bits
     */
    public static float bytes2float(byte[] bytes) {
        assert (bytes.length == 4);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        FloatBuffer fb = bb.asFloatBuffer();
        return fb.get();
    }

    /**
     * author:cyl
     *
     * @param bytes
     *            (byte[])
     * @return retInt(int) function: 把byte[]解析成int类型 assert :byte[]数组大小在1到3之间
     */
    public static int bytes2int(byte[] bytes) // 解析服务端给的包的时候用
    {
        int bytesnum = bytes.length; // 获得 字节数
        int retInt = 0x00000000; // 用来做位或操作
        assert (bytesnum < 4 && bytesnum > 0);
        for (int i = 0; i < bytesnum; i++) {
            int leftchangebitsnum = (bytesnum - i - 1) * 8;
            int tempint = (int) (bytes[i]) & 0xff; // 0xff防止它先转化成数值 再转换
            // 确保是按位进行操作的
            // byte to int 它先转化成数值再转化过去

            if (leftchangebitsnum > 0)
                tempint = tempint << leftchangebitsnum;
            retInt = retInt | tempint;
        }
        return retInt;
    }

    /**
     * author:cyl
     *
     * @param operatedinteger
     *            (int)
     * @param bytesnum
     *            (int)
     * @return function:int解析成byte[] 包装包的时候用 assert:bytesnum<=4
     */
    public static byte[] int2bytes(int operatedinteger, int bytesnum) // 构建包的时候用
    {
        // 一个Int 4个Byte
        assert (bytesnum <= 4);
        int index = 0;
        operatedinteger = operatedinteger & (getHex(bytesnum));
        byte[] bytes = new byte[bytesnum];
        for (int i = 0; i < bytesnum; i++) {
            int tempinteger = operatedinteger;
            // 得先向左移位 后到顶再向右移位到底才行
            int leftchangebitnum = (4 - bytesnum + i) * 8;
            tempinteger = tempinteger << leftchangebitnum;
            int rightchangebitnum = 24;

            tempinteger = tempinteger >> rightchangebitnum;
            bytes[index] = (byte) ((tempinteger));
            index++;

        }
        return bytes;
    }

    // ｃｙｌ　　创建全０的数组
    public static int[] createZeros(int length) {
        int[] retints = new int[length];
        for (int i = 0; i < length; i++) {
            retints[i] = 0;
        }
        return retints;
    }

}
