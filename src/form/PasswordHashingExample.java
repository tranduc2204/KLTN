/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author TeeDee
 */
public class PasswordHashingExample {
    private static final int ROUNDS = 12;

    public static String hashPassword(String plainTextPassword) {
        // Tạo salt ngẫu nhiên
        String salt = BCrypt.gensalt(ROUNDS);

        // Mã hóa mật khẩu bằng cách sử dụng salt
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        // Kiểm tra mật khẩu nhập vào có khớp với mật khẩu đã lưu không
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
}
