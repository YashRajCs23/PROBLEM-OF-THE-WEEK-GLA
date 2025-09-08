public class SwapEvenOddBits {
    //Tc: O(1) Sc: O(1)
    //approach: use masks to extract even and odd bits, shift them and combine
    public static int swapBits(int n) {
        int even_mask = 0xAAAAAAAA;
        int odd_mask = 0x55555555;
        int even_bits = n & even_mask;
        int odd_bits = n & odd_mask;
        even_bits >>= 1;
        odd_bits <<= 1;
        return (even_bits | odd_bits);
    }
}