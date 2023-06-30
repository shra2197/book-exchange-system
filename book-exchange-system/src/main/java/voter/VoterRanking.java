package voter;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Input : [
 * [
 * A, B, C
 * A, C, D
 * D, A, C
 * ]]
 * Output : [A, D , C , B]
 */


public class VoterRanking {

    public static String candidateWinner(String[] votes) {
        Map<Character, int[]> candidateMap = new HashMap<>();
        List<Character> candidates = new ArrayList<>();
        for (int i = 0; i < votes[0].length(); i++) {
            candidateMap.put(votes[0].charAt(i), new int[votes[0].length()]);
            candidates.add(votes[0].charAt(i));
        }

        for (int i = 0; i < votes.length; i++) {
           String votesFromUser=votes[i];
            for (int j = 0; j < votesFromUser.length(); j++) {
                candidateMap.get(votesFromUser.charAt(j))[j] += 1;
            }
        }

        candidates.sort((a, b) -> {
            int[] vote1 = candidateMap.get(a);
            int[] vote2 = candidateMap.get(b);
            for (int i = 0; i < vote1.length; i++) {
                if (vote1[i] < vote2[i]) return 1;
                if (vote1[i] > vote2[i]) return -1;
            }
            return a.compareTo(b);
        });

        StringBuilder sb = new StringBuilder();
        for(Character c : candidates) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] votes = {
                "ABCD",
                "ACDB",
                "DACB"
        };
        System.out.println(candidateWinner(votes));
    }
}
