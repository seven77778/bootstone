package com.lsh.demo.network.serial;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;

public class CISAV2Request implements Serializable {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("lockLocalLog");
    private static final Charset charset = Charset.forName("ASCII");
    // Start of Frame ; 1 byte
    private static final byte STX = 0x2;
    // End of Frame ;1 byte
    private static final byte ETX = 0x3;
    // Hotel identifier ；6 bytes
    private String hotelIdentifier;
    // Available commands:
    // 01 = new card issue
    // 02 = read card information
    // 03 = card copy issue
    // 04 = guest check-out
    private String command;
    // Client Number ；2 bytes
    private String clientNum;
    // Encoder number ；2 bytes
    private String encoderNum; //TODO CONFIRM
    // Last name ; 15 bytes
    private String firstName;
    //Last name ; 15 bytes
    private String lastName;
    // Room number (1) ; 6 bytes
    private String roomNum1; //6 (5-0)
    // Check-in date ; 8 bytes "20100101"
    private String checkInDate;
    // Check-in time ; 4 bytes "1452"
    private String checkInTime;
    // Check-out date ; 8 bytes "20100102"
    private String checkOutDate;
    // Check-out time ; 4 bytes "1452"
    private String checkOutTime;
    // Room number (2) ; 6 bytes
    private String roomNum2;
    // Room number (3) ; 6 bytes
    private String roomNum3;
    // Room number (4) ; 6 bytes
    private String roomNum4;


    public CISAV2Request() {
    }

    public String getHotelIdentifier() {
        return hotelIdentifier;
    }

    public void setHotelIdentifier(String hotelIdentifier) {
        this.hotelIdentifier = hotelIdentifier;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getClientNum() {
        return clientNum;
    }

    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }

    public String getEncoderNum() {
        return encoderNum;
    }

    public void setEncoderNum(String encoderNum) {
        this.encoderNum = encoderNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoomNum1() {
        return roomNum1;
    }

    public void setRoomNum1(String roomNum1) {
        this.roomNum1 = roomNum1;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getRoomNum2() {
        return roomNum2;
    }

    public void setRoomNum2(String roomNum2) {
        this.roomNum2 = roomNum2;
    }

    public String getRoomNum3() {
        return roomNum3;
    }

    public void setRoomNum3(String roomNum3) {
        this.roomNum3 = roomNum3;
    }

    public String getRoomNum4() {
        return roomNum4;
    }

    public void setRoomNum4(String roomNum4) {
        this.roomNum4 = roomNum4;
    }



    /**
     * 组装报文
     *  <STX>	02	1	Start of Frame
     *  <Data>	N	N	Data sent to the receiving station (ASCII character set between 0x20 and 0xFF)
     *  <CSUM>	N	2	Checksum
     *  <EXT>	03	1	End of Frame
     */
    public byte[] toBytes() throws IOException {
        byte [] resultBytes =  new byte[103];
        resultBytes[0] =  STX;
        resultBytes[102] =ETX;
        StringByteArrayOutputStream strbu = new StringByteArrayOutputStream(charset);

//        StringBuilder strbu = new StringBuilder();
        //2-7	6	"334861"	Hotel identifier
        strbu.write(CISAV2Utils.padRight("0000003801000000002100000000BILL", 33, " "));
        //8-9	2	“38”	Constant value
//        strbu.write(CISAV2Utils.padRight("01", 2, "0"));
//        //10-11	2	“01”	Available commands:
//        //Available commands:
//        //01 = new card issue
//        //02 = read card information
//        //03 = card copy issue
//        //04 = guest check-out
//        strbu.write(CISAV2Utils.padRight("00000000", 8, "0"));
//        //12-19	8	“00000000"	Constant value
//        strbu.write(CISAV2Utils.padRight("0", 8, "0"));
//        //20-21	2	“02”	Client Number
//        strbu.write(CISAV2Utils.padRight("02", 2, "0"));
//        //22-23	2	“00	Constant value
//        strbu.write(CISAV2Utils.padRight("00", 2, "0"));
//        //24-25	2	“03”	Encoder number
//        strbu.write(CISAV2Utils.padRight("03", 2, "0"));
//        //26-29	4	“0000”	Constant value
//        strbu.write(CISAV2Utils.padRight("0000", 4, "0"));
        //30-44	15	“PAOLO          “	First name
        strbu.write(CISAV2Utils.padRight("Li", 10, " "));
        //45-59	15	“OSSI           “	Last name
        strbu.write(CISAV2Utils.padRight("Wang", 15, " "));
        //60-65	6	“0301  “	Room number (1)
        strbu.write(CISAV2Utils.padRight("5915", 6, " "));
        //66-73	8	“20100101”	Check-in date yyyyMMDD
        strbu.write(CISAV2Utils.padRight("20190311", 8, "0"));
        //74-77	4	“1452”	Check-in time HHMM
        strbu.write(CISAV2Utils.padRight("0000", 4, "0"));
        //78-85	8	“20100108”	Check-out date
        strbu.write(CISAV2Utils.padRight("20190312", 8, "0"));
        //86-89	4	“1200”	Check-out time
        strbu.write(CISAV2Utils.padRight("1500", 4, "0"));
        //90-97	8	“00000000”	Constant value
        strbu.write(CISAV2Utils.padRight("00000000",8,"0"));
        //98-100	3	“001”	Constant value
        strbu.write("001");

        LOGGER.info("> CISA 封装参数："+strbu.toString().toUpperCase());
        byte[] msgByte = strbu.toString().toUpperCase().getBytes();
        LOGGER.info("> CISA request 参数报文:"+Arrays.toString(msgByte));
        System.arraycopy(msgByte,0,resultBytes,1,msgByte.length);
        //CRC
        byte [] checkSum = CISAV2Utils.calcCheckSum(msgByte);
        LOGGER.info("> CISA request 校验码:"+ Arrays.toString(checkSum));
        System.arraycopy(checkSum,0,resultBytes,100,checkSum.length);
        LOGGER.info("> CISA request 组装后报文："+Arrays.toString(resultBytes));
        return resultBytes;
    }
}
