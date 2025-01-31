package gr.aueb.practiceProjects;

/**
 * To πρόβλημα θα μπορούσε να λυθεί βρίσκοντας όλα τα αθροίσματα των πιθανών
 * contiguous sub-arrays κάνοντανας nesting 2 for. H πρώτη με
 * 'int i = 0; i < arr.length; i++'
 * θα εκανε traverse ένα - ένα τα στοιχεία του πίνακα δίνοντας μας το πρώτο
 * στοιχείο όλων των πινάκων του εξωτερικού for και χρησιμοποιώντας μία
 * δεύτερη εξωτερική for με
 * 'int j = i; j < arr.length; j++'
 * θα πέρναμε όλους τους πιθανούς πίνακες. Ταυτόχρονα, χρησιμοποιώντας τις
 * μεταβλητές globalMaxSum και currentMaxSum θα ελέγχαμε το currentMaxSum
 * και άν είναι μεγαλυτερο απο το globalMaxSum, θα του περνούσαμε την τιμή.
 *.
 * Σε αύτην την περίπτωση, η πολυπλοκότητα χρόνου μέχρι τώρα θα ήτανε τουλάχιστον
 * Ο(n^2) γιατί έχουμε δυο nested for που διατρέχουν όλα τα στοιχεία του πίνακα.
 * .
 * .
 * Η ποιό αποδότικη μέθοδος, με γραμμικό χρόνο Ο(n) είναι να διατρέξουμε μία
 * φορά τον πίνακα και να ελέγχουμε για κάθε βήμα το "best solution so far.
 * Χρησιμοποιώντας δυναμικό προγραμματισμό και τον αλγοριθμο του kadane, σπάμε
 * το πρόβλημα σε συνεχόμενα υποπροβλήματα που όλα όμως διέπονται από την ίδια
 * αρχή.
 * .
 * Επειδή:
 * 1. το sum του sub-array πρεπει να είναι σε συνεχόμενο sub-array.
 * 2. γνωρίζουμε το προηγούμε currentMax: <<currentMax(arr(i-1))>>
 * Τότε:
 * 1. Αν η πρόσθεση του arr(i) οδηγεί σε μεγαλύτερο sum, συνεχίζουμε να προσθέτουμε
 * αφού για την θεση i το μέγιστο άθροισμα του sub-array που τελειώνει στο στοιχείο i θα
 * είναι το currentMax(arr(i-1)) + arr(i).
 * 2. Αν η πρόσθεση του arr(i) δεν οδηγεί σε μεγαλύτερο sum, σταματάμε να προσθέτουμε
 * αφού το μέγιστο άθροισμα του sub-array που τελειώνει στο στοιχείο i είναι το
 * currentMax(arr(i-1)).
 * Σε αυτό ακριβώς το σημείο, ξερουμε ότι προσθέτοντας το arr(i) στο sub-array δεν θα
 * μας δώσει μεγαλύτερο αθροισμα. Το μέγιστο άθροισμα για αυτό το sub-array έχει ήδη
 * είναι στην προηγούμενη θέση και το έχουμε αποθηκεύσει στο globalMax.
 * Αυτό που μένει να ελέγξουμε τώρα είναι αν κάποιο επόμενο αθροισμα sub-array θα υπερβεί
 * το globalMax. Οπότε ξεκινάμε από ένα νέο sub-array, θέτοντας ως currentMax το arr(i)
 * προσθέτοντας όλα τα στοιχεία που οδηγούν σε νέο μεγαλυτερο αθροισμα, τσεκάροντας ταυτόχρονα
 * αν το currentMax υπερβαινει το globalMax, και αν ισχύει θέτουμε ως globalMax το νεο
 * μεγαλύτερο άθροισμα.
 * Μέχρι να φτάσουμε στο στοιχείο που η πρόσθεση του δεν αυξάνει το currentMax, άρα
 * το currentMax αυτου του sub-array έχει ήδη βρεθεί (και συγκριθεί με το globalMax)
 * και αρχίζουμε τον έλεγχο για ένα νεο sub-array θέτοντας παλι currentMax = arr(i).
 * .
 * Η πολυπλοκότητα χρόνου είναι Ο(n), αφου γίνεται ένα traverse του πίνακα, άρα ο χρόνος
 * εκτέλεσης του προγράματος είναι ανάλογος με τον αριθμό των στοιχείων n, και αυξάνεται
 * γραμμικά ανάλογο με το πλήθος τους.
 */
public class Project02 {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("Maximum Sub-array Sum: " + maxSubArray(arr));
    }

    public static int maxSubArray(int[] arr) {
        //θέτω currentMaxSum την τιμή του πρώτου στοιχείου και globalMaxSum
        // την ελάχιστη δυνατή τιμή που μπορεί να πάρει.
        int currentMaxSum = arr[0];
        int globalMaxSum = Integer.MIN_VALUE;

        //Ξεκινάω απο το δεύτερο στοιχείο και διατρέχω όλο τον πίνακα κανοντας του απαραίτητους ελέγχους
        for (int i = 1; i < arr.length; i++) {
            currentMaxSum  = Math.max(currentMaxSum + arr[i], arr[i]);
            globalMaxSum = Math.max(globalMaxSum, currentMaxSum);
        }

        return globalMaxSum;
    }
}
