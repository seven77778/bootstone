package work.enums;

import org.assertj.core.util.Lists;

import java.util.Optional;

/**
 * Created by lsh on 2019-04-23.
 *
 * 枚举风骚写法
 *         return res.orElse(null);
 *
 */
public enum EnumDemo1 {

    /**
     *
     */
    Room1("7777","0","0","012"),
    Room2("8888","0","0","111"),
    Room195("9999","1","1","911");


    private String RoomName;
    private String build;
    private String floor;
    private String room;

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    EnumDemo1(String RoomName, String build, String floor, String room){
        this.build = build;
        this.floor=floor;
        this.room = room;
        this.RoomName=RoomName;
    }

    public static EnumDemo1 getRoomEnum(String roomName){
        Optional<EnumDemo1> res = (Lists.newArrayList(EnumDemo1.values()).stream().filter(x -> x.RoomName.equals(roomName)).findFirst());
        return res.orElse(null);
    }

}
