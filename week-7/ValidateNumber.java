public class ValidateNumber {
    /**
     * ---------------------------------------------------
     * Approach 1: One-pass Parser with Flags
     * ---------------------------------------------------
     * Idea:
     *  - Trim spaces
     *  - Use flags:
     *      seenDigit, seenDot, seenExp, digitAfterExp
     *  - Rules:
     *      - Digits: mark seenDigit (and digitAfterExp if after exp)
     *      - Dot: only once, not after e/E
     *      - Exp: only once, only if digit seen before, must be followed by digits
     *      - Signs: only at start or just after e/E
     *  TC: O(n)  (scan string once)
     *  SC: O(1)  (just flags)
     *  Type: Better Approach
     */
    public static boolean isNumberParser(String s) {
        s = s.trim();
        boolean seenDigit = false, seenDot = false, seenExp = false, digitAfterExp = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
                if (seenExp) digitAfterExp = true;

            } else if (c == '+' || c == '-') {
                // only allowed at start or just after e/E
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }

            } else if (c == '.') {
                // only one dot, not after e/E
                if (seenDot || seenExp) return false;
                seenDot = true;

            } else if (c == 'e' || c == 'E') {
                // only one exp, must follow a digit
                if (seenExp || !seenDigit) return false;
                seenExp = true;
                digitAfterExp = false; // reset, must find digit later

            } else {
                return false;
            }
        }
        return seenDigit && digitAfterExp;
    }

    /**
     * ---------------------------------------------------
     * Approach 2: Regex
     * ---------------------------------------------------
     * Regex: ^\s*[+-]?((\d+(\.\d*)?)|(\.\d+))([eE][+-]?\d+)?\s*$
     * - optional spaces
     * - optional sign
     * - number in integer/decimal form
     * - optional exponent
     *  TC: O(n)  (regex engine parses string once)
     *  SC: O(1)
     *  Type: Optimal (shortest code, practical)
     */
    public static boolean isNumberRegex(String s) {
        String regex = "^\\s*[+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([eE][+-]?\\d+)?\\s*$";
        return s.matches(regex);
    }

    /**
     * ---------------------------------------------------
     * Approach 3: DFA (Finite State Machine)
     * ---------------------------------------------------
     * States:
     * 0 -> start (space/sign/digit/dot)
     * 1 -> sign before number
     * 2 -> integer digits
     * 3 -> dot after integer
     * 4 -> dot without leading digit
     * 5 -> fraction digits
     * 6 -> exp symbol
     * 7 -> exp sign
     * 8 -> exp digits
     *
     * Valid end states: 2,3,5,8
     *
     *  TC: O(n)
     *  SC: O(1)
     *  Type: Brute/Verbose but very formal
     */
    public static boolean isNumberDFA(String s) {
        s = s.trim();
        int state = 0;
        boolean seenDigit = false, expSeenDigit = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (state) {
                case 0: // start
                    if (c == '+' || c == '-') state = 1;
                    else if (Character.isDigit(c)) { state = 2; seenDigit = true; }
                    else if (c == '.') state = 4;
                    else return false;
                    break;

                case 1: // sign
                    if (Character.isDigit(c)) { state = 2; seenDigit = true; }
                    else if (c == '.') state = 4;
                    else return false;
                    break;

                case 2: // integer
                    if (Character.isDigit(c)) { seenDigit = true; }
                    else if (c == '.') state = 3;
                    else if (c == 'e' || c == 'E') {
                        if (!seenDigit) return false;
                        state = 6;
                    } else return false;
                    break;

                case 3: // dot after integer
                    if (Character.isDigit(c)) { state = 5; seenDigit = true; }
                    else if (c == 'e' || c == 'E') {
                        if (!seenDigit) return false;
                        state = 6;
                    } else return false;
                    break;

                case 4: // dot without leading digit
                    if (Character.isDigit(c)) { state = 5; seenDigit = true; }
                    else return false;
                    break;

                case 5: // fraction digits
                    if (Character.isDigit(c)) { seenDigit = true; }
                    else if (c == 'e' || c == 'E') {
                        if (!seenDigit) return false;
                        state = 6;
                    } else return false;
                    break;

                case 6: // exp
                    if (c == '+' || c == '-') state = 7;
                    else if (Character.isDigit(c)) { state = 8; expSeenDigit = true; }
                    else return false;
                    break;

                case 7: // exp sign
                    if (Character.isDigit(c)) { state = 8; expSeenDigit = true; }
                    else return false;
                    break;

                case 8: // exp digits
                    if (Character.isDigit(c)) { expSeenDigit = true; }
                    else return false;
                    break;

                default: return false;
            }
        }

        // valid end states
        return (state == 2 || state == 3 || state == 5 || state == 8) && seenDigit && (state != 6 && state != 7) && (state != 8 || expSeenDigit);
    }
}