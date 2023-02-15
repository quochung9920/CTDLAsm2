import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Double> array = new ArrayList<Double>();

    static void menu() {
        System.out.println("+-------------------Menu------------------+");
        System.out.println("|   1.Manual Input                        |");
        System.out.println("|   2.File input                          |");
        System.out.println("|   3.Bubble sort                         |");
        System.out.println("|   4.Selection sort                      |");
        System.out.println("|   5.Insertion sort                      |");
        System.out.println("|   6.Search > value                      |");
        System.out.println("|   7.Search = value                      |");
        System.out.println("|   0.Exit                                |");
        System.out.println("+-----------------------------------------+");
    }

    /** Hàm nhập chức năng */
    public static String inputFunction(Scanner scanner, String message) {
        System.out.print(message);
        System.out.print("\033[32m");
        String choice = scanner.next();
        System.out.print("\033[0m");
        return choice;
    }

    /** Hàm lựa chọn chức năng của menu chính */
    public static void parseFunction(Scanner scanner, String choice) {

        switch (choice) {
            case "1":
                manualInput(scanner);
                break;
            case "2":
                fileInput(scanner);
                break;
            case "3":
                bubbleSort(array);
                break;
            case "4":
                selectionSort(array);
                break;
            case "5":
                insertionSort(array);
                break;
            case "6":
                searchMoreThanValue(array);
                break;
            case "7":
                searchEqualValue(array);
                break;
            case "0":
                // Thoát chương trình
                System.out.println("Thanks!!!");
                System.exit(0);
                break;
        }
        // Sau khi thực hiện xong chức năng, hiển thị menu chính
        menu();
        inputFunctionMain(scanner);
    }

    /**
     * Hàm triển khai chức năng của menu chính
     * Khi người dùng nhập vào chức năng, hàm này sẽ gọi hàm parseFunction để triển
     * khai chức năng
     */
    public static String inputFunctionMain(Scanner scanner) {
        String choice = "";
        try {
            choice = inputFunction(scanner, "Enter selection: ");
            // Kiểm tra lựa chọn chức năng
            if (choice.matches("[0-7]") && !choice.isEmpty()) {
                parseFunction(scanner, choice);
                return choice;
            } else {
                throw new Exception("Please enter a number from 0 to 7!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputFunctionMain(scanner);
        }
    }

    // Chức năng 1: Manual Input
    public static void manualInput(Scanner scanner) {
        int numberElements = Integer.parseInt(inputFunction(scanner, "Please enter input number of elements: "));
        double[] arr = new double[numberElements];
        System.out.println("Please enter input elements: ");
        for (int i = 0; i < numberElements; i++) {
            arr[i] = Double.parseDouble(inputFunction(scanner, ""));
        }
        // Copy mảng arr sang mảng array
        for (int i = 0; i < arr.length; i++) {
            array.add(arr[i]);
        }

        // Kiểm tra file Input.txt có dữ liệu hay không, nếu có thì xóa dữ liệu
        if (TextFileService.readFile("file/Input.txt").length() > 0) {
            TextFileService.writeFile("file/Input.txt", "");
        }

        // Ghi mảng array vào file Input.txt
        String content = "";
        for (int i = 0; i < array.size(); i++) {
            content += array.get(i) + " ";
        }
        TextFileService.writeFile("file/Input.txt", content);
    }

    // Chức năng 2: File input
    public static void fileInput(Scanner scanner) {
        try {
            String fileName = inputFunction(scanner, "Please enter the file path: ");
            String content = TextFileService.readFile(fileName);
            String[] arr = content.trim().split(" ");
            System.out.printf("Input array: ");
            for (int i = 0; i < arr.length; i++) {
                array.add(Double.parseDouble(arr[i]));
                System.out.printf("%.0f ", Double.parseDouble(arr[i]));
            }
            System.out.println();
        } catch (Exception e) {
            
        }
    }

    // Chức năng 3: Bubble sort
    public static void bubbleSort(ArrayList<Double> arr) {
        int n = arr.size(); // Sử dụng method size() để lấy số lượng phần tử trong ArrayList
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) { // Sử dụng method get() để lấy giá trị từ ArrayList
                    double temp = arr.get(j);
                    arr.set(j, arr.get(j + 1)); // Sử dụng method set() để gán giá trị cho ArrayList
                    arr.set(j + 1, temp);
                }
            }
            printArray(arr);
        }

        // Sau khi sắp xếp xong, ghi kết quả vào file Output1.txt
        String output = "";
        for (int i = 0; i < arr.size(); i++) {
            output += arr.get(i) + " ";
        }
        TextFileService.writeFile("file/Output1.txt", output);
    }

    // Chức năng 4: Selection sort
    public static void selectionSort(ArrayList<Double> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }
            double temp = arr.get(minIndex);
            arr.set(minIndex, arr.get(i));
            arr.set(i, temp);
            printArray(arr);
        }

        // Sau khi sắp xếp xong, ghi kết quả vào file Output2.txt
        String output = "";
        for (int i = 0; i < arr.size(); i++) {
            output += arr.get(i) + " ";
        }
        TextFileService.writeFile("file/Output2.txt", output);
    }

    // Chức năng 5: Insertion sort
    public static void insertionSort(ArrayList<Double> arr) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            double key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
            printArray(arr);
        }

        // Sau khi sắp xếp xong, ghi kết quả vào file Output3.txt
        String output = "";
        for (int i = 0; i < arr.size(); i++) {
            output += arr.get(i) + " ";
        }
        TextFileService.writeFile("file/Output3.txt", output);
    }

    // Chức năng 6: Search > value
    public static void searchMoreThanValue(ArrayList<Double> arr) {
        double value = Double.parseDouble(inputFunction(scanner, "Please enter searched input value: "));
        int count = 0;
        System.out.print("Larger position: ");
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > value) {
                System.out.printf("%.0f ", arr.get(i));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No value found!");
        }
        System.out.println();
    }

    // Chức năng 7: Search = value
    public static void searchEqualValue(ArrayList<Double> arr) {
        double value = Double.parseDouble(inputFunction(scanner, "Please enter searched input value: "));
        int count = 0;
        System.out.printf("The right position: ");
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == value) {
                System.out.printf("%.0f ", arr.get(i));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No value found!");
        }
        System.out.println();
    }

    public static void printArray(ArrayList<Double> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        
        menu();
        inputFunctionMain(scanner);

    }
}
