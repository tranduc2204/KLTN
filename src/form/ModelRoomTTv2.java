/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

/**
 *
 * @author TeeDee
 */
class ModelRoomTTv2 {
    private String matinhtrangphong, tinhtrangphong;

    public ModelRoomTTv2() {
    }

    public ModelRoomTTv2(String matinhtrangphong, String tinhtrangphong) {
        this.matinhtrangphong = matinhtrangphong;
        this.tinhtrangphong = tinhtrangphong;
    }

    public String getMatinhtrangphong() {
        return matinhtrangphong;
    }

    public void setMatinhtrangphong(String matinhtrangphong) {
        this.matinhtrangphong = matinhtrangphong;
    }

    public String getTinhtrangphong() {
        return tinhtrangphong;
    }

    public void setTinhtrangphong(String tinhtrangphong) {
        this.tinhtrangphong = tinhtrangphong;
    }
}
