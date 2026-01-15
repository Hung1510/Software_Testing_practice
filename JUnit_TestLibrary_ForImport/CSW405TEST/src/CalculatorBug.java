public class CalculatorBug {

    // BUG: viết nhầm phép cộng thành phép trừ
    public int add(int a, int b) {
        return a - b;   // ❌ LỖI LOGIC // chỗ này sai dấu phải là dấu cộng
    }
}
