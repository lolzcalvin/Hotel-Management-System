package application.assets;
import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

public class ModelRoom {
    private SimpleStringProperty roomcat = new SimpleStringProperty();
    private SimpleStringProperty roomno = new SimpleStringProperty();
    private SimpleStringProperty rtype = new SimpleStringProperty();
    private SimpleStringProperty cidate = new SimpleStringProperty();
    private SimpleStringProperty codate = new SimpleStringProperty();
    private SimpleStringProperty extbedtype = new SimpleStringProperty();
    private SimpleStringProperty roomprice = new SimpleStringProperty();
//    private SimpleStringProperty paxperroom = new SimpleStringProperty();
//    private SimpleStringProperty twinbedprice = new SimpleStringProperty();
//    private SimpleStringProperty fullbedprice = new SimpleStringProperty();
//    private SimpleStringProperty queenbedprice  = new SimpleStringProperty();
//    private SimpleStringProperty kingbedprice  = new SimpleStringProperty();

    public SimpleStringProperty roomcatProperty() {
        return roomcat;
    }

    public SimpleStringProperty roomnoProperty() {
        return roomno;
    }

    public SimpleStringProperty rtypeProperty() {
        return rtype;
    }

    public SimpleStringProperty cidateProperty() {
        return cidate;
    }

    public SimpleStringProperty codateProperty() { return codate;}

    public SimpleStringProperty extbedtypeProperty() {
        return extbedtype;
    }

    public SimpleStringProperty roompriceProperty() {
        return roomprice;
    }

//    public SimpleStringProperty paxperroomProperty() { return  paxperroom; }
//
//    public SimpleStringProperty twinbedpriceProperty() { return  twinbedprice; }
//
//    public SimpleStringProperty fullbedpriceProperty() { return  fullbedprice; }
//
//    public SimpleStringProperty queenbedpriceProperty() { return  queenbedprice; }
//
//    public SimpleStringProperty kingbedpriceProperty() { return  kingbedprice; }

    public String getRoomcat() {
        return roomcat.get();
    }

    public String getRoomno() {
        return roomno.get();
    }

    public String getRtype() {
        return rtype.get();
    }

    public String getCidate() {
        return cidate.get();
    }

    public String getCodate() {
        return codate.get();
    }

    public String getExtbedtype() {
        return extbedtype.get();
    }

    public String getRoomprice() {
        return roomprice.get();
    }

//    public String getPaxPerRoom() { return paxperroom.get(); }
//
//    public String getTwinBedPrice() { return twinbedprice.get(); }
//
//    public String getFullBedPrice() { return fullbedprice.get(); }
//
//    public String getQueenBedPrice() { return queenbedprice.get(); }
//
//    public String getKingBedPrice() { return kingbedprice.get(); }

    //these setters are important to set database column value into each properties of this data model class


    public void setRoomcat(String roomcat) {
        this.roomcat.set(roomcat);
    }

    public void setRoomno(String roomno) {
        this.roomno.set(roomno);
    }

    public void setRtype(String rtype){
        this.rtype.set(rtype);
    }

    public void setCidate(String cidate) {
        this.cidate.set(cidate);
    }

    public void setCodate(String codate) {
        this.codate.set(codate);
    }

    public void setExtbedtype(String extbedtype) {
        this.extbedtype.set(extbedtype);
    }

    public void setRoomprice(String roomprice) {
        this.roomprice.set(roomprice);
    }

//    public void setPaxperroom(String paxperroom) { this.paxperroom.set(paxperroom); }
//
//    public void setTwinbedprice(String twinbedprice) { this.twinbedprice.set(twinbedprice); }
//
//    public void setFullbedprice(String fullbedprice) { this.fullbedprice.set(fullbedprice); }
//
//    public void setQueenbedprice(String queenbedprice) { this.queenbedprice.set(queenbedprice); }
//
//    public void setKingbedprice(String kingbedprice) { this.kingbedprice.set(kingbedprice); }

    //if u don't understand getters and setters then ggwp
}

