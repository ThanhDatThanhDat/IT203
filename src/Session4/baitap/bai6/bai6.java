package Session4.baitap.bai6;

import java.util.regex.*;

public class bai6 {
    public static void main(String[] args) {
        String review = "Cuon sach nay rat te, noi dung that la ngu ngoc va khong dang doc.";
        String[] blacklist = {"te", "ngu ngoc", "khong dang"};

        String regex = String.join("|", blacklist);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(review);
        StringBuilder filteredReview = new StringBuilder();

        int lastEnd = 0;
        while (matcher.find()) {
            filteredReview.append(review, lastEnd, matcher.start());

            int length = matcher.group().length();
            filteredReview.append("*".repeat(length));

            lastEnd = matcher.end();
        }
        filteredReview.append(review.substring(lastEnd));

        String result = filteredReview.toString();

        if (result.length() > 200) {
            int cutIndex = result.substring(0, 200).lastIndexOf(" ");
            if (cutIndex == -1) {
                cutIndex = 200;
            }

            StringBuilder shortReview = new StringBuilder();
            shortReview.append(result, 0, cutIndex);
            shortReview.append("...");

            result = shortReview.toString();
        }

        System.out.println("Review sau khi su ly:");
        System.out.println(result);
    }
}