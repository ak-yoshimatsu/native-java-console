package app;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== ユーザー管理メニュー ===");
                System.out.println("1. ユーザー追加");
                System.out.println("2. ユーザー一覧表示");
                System.out.println("3. ユーザー検索（ID）");
                System.out.println("4. ユーザー削除（ID）");
                System.out.println("0. 終了");
                System.out.print("選択 > ");
                
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                case 1:
                    System.out.println("ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("名前: ");
                    String name = scanner.nextLine();
                    System.out.println("メールアドレス: ");
                    String email = scanner.nextLine();
                    userService.addUser(new User(id, name, email));
                    System.out.println("ユーザーを追加しました。");
                    break;
                case 2:
                    List<User> users = userService.getAllUsers();
                    for (User user: users) {
                        System.out.println(user);
                    }
                    break;
                case 3:
                    System.out.println("検索するID: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    User user = userService.findUserById(searchId);
                    if (user != null) {
                        System.out.println(user);
                    } else {
                        System.out.println("該当ユーザーが見つかりません。");
                    }
                    break;
                case 4:
                    System.out.println("削除するID: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    if (userService.deleteUserById(deleteId)) {
                        System.out.println("削除しました。");
                    } else {
                        System.out.println("ユーザーが見つかりません。");
                    }
                    break;
                case 0:
                    System.out.println("終了します。");
                    return;
                default:
                    throw new IllegalArgumentException("無効な選択です。 value: " + choice);
                }
            }
        } catch (NumberFormatException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

}
