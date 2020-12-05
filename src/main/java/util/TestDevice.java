package util;

public class TestDevice {
    private final String name;
    private final String os;

    public TestDevice(String os, String name) {
        this.os = os;
        this.name = name;
    }


    public String getOS() {
        return os;
    }

    public String getDevice(){
        return name;
    }

    public String toString() {
        return String.format("Device Name: %s | Browser: %s ", this.name, os);
    }

    //-----------------------------------DEBUG MODE-----------------------------------------------------------------

    public String toStringDevice(){
        final String deviceString = "\n"+
                "\t     ____________   \n"+
                "\t   /____________/|  \n"+
                "\t  | ___________ ||  \n"+
                "\t  ||           |||    "+ "  Device Name:  "+getDevice()+"\n"+
                "\t  ||           |||    "+
                "\t  ||           |||    "+ "  OS         :  "+ getOS()+"\n"+
                "\t  ||___________|||    "+
                "\t  |   _______   ||  \n"+
                "\t  |  (_______)  ||  \n"+
                "\t  |_____________|/   \n"+
                "\t                     \n";
        return deviceString;
    }
}
