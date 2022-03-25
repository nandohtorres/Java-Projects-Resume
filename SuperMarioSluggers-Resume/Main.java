// Fernando Torres
// FXT160530
// importing java libraries needed to run code

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    // main method in our Main class, opens scanner for user input and calls method in order
    public static void main(String[] args) throws IOException {
        // creates new scanner called "input" for user input
        Scanner input = new Scanner(System.in);
        // creates a string called filename and initialized it to take in the next line of user input
        String filename = input.nextLine();
        // tries to open the file with "filename" using the scanner
        // if it were to not succeed the catch would throw a FileNotFound exception and output File not Found
        try {
            input = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // sets list equal to readingInputFile method in order to populate the list
        LinkList list = readingInputFile(input);
        // calls out alphabetize method in LinkList class to put the list in order alphabetically
        list.alphabetize();
        // calls our print function in LinkList class to print it out
        list.print();
        // calls leagueLeaders method and passes the argument list
        leagueLeaders(list);
        //closing scanner for good coding practice
        input.close();
    }

    // this method opens a new scanner to populate the LinkList
    public static LinkList readingInputFile(Scanner input) {
        //opens the file making sure the name is sample_stats2.txt
        //FileInputStream file = new FileInputStream("sample_stats2.txt");
        //Scanner scan = new Scanner(file);
        // created variables name and string in order to use in while loop to populate list
        String name;
        String stats;
        // create new LinkList list
        LinkList list = new LinkList();
        // while loop will execute for as many lines as data the input file has
        while (input.hasNextLine()) {
            // we grab the first line in file then trim it
            String line = input.nextLine().trim();
            // checks to see if there is a blank line and is so code continues to run
            if (line.length() == 0) {
                continue;
            }
            // we trim from beginning of string to the first blank space and that is the players name
            name = line.substring(0, line.indexOf(" "));
            // we trim from first blank space to the end of the data
            stats = line.substring(line.indexOf(" ") + 1);

            // checking to see if we have multiple data rows for one player
            // merges the data together

            boolean found = false;
            Iterator<Player> it = list.iterator();
            while (it.hasNext()) {
                Player player = it.next();
                if (player.getName().equals(name)) {
                    player.addStats(stats);
                    found = true;
                    break;
                }
            }

            // creates new "player" and passes the arguments name and stats
            if (!found) {
                Player player = new Main.Player(name, stats);
                // adds player to list
                list.add(player);
            }
        }

        // closes scanner after while loop
        input.close();
        // returns the LinkList called list
        return list;
    }

    // created a PlayerStat inner class to hold name and stat
    static class PlayerStat {
        private String name;
        private double stat;

        public PlayerStat(String name, double stat) {
            this.name = name;
            this.stat = stat;
        }

        public String getName() {
            return name;
        }

        public double getStat() {
            return stat;
        }

        public String toString() {
            return getName() + "\t" + getStat();
        }

    }

    // created a Player inner class
    static class Player {
        // create a character array with the valid results from data
        public final char[] VALID_RESULTS = new char[]{'H', 'O', 'K', 'W', 'P', 'S'};
        // creating all variables needed to keep track of
        private String name;
        private int hits;
        private int outs;
        private int strikeout;
        private int walks;
        private int hitByPitch;
        private int sacrifice;

        public Player(String name, String stats) {
            this.name = name;
            addStats(stats);
        }

        //
        public Player(String name, int hits, int outs, int strikeout, int walks, int hitByPitch, int sacrifice) {
            this.name = name;
            this.hits = hits;
            this.outs = outs;
            this.strikeout = strikeout;
            this.walks = walks;
            this.hitByPitch = hitByPitch;
            this.sacrifice = sacrifice;
        }

        // parses through the string and increments the data by 1 for each valid result
        public void addStats(String stats) {
            for (int j = 0; j < stats.length(); j++) {
                char c = stats.charAt(j);
                if (c == 'H') {
                    hits++;
                }
                if (c == 'W') {
                    walks++;
                }
                if (c == 'K') {
                    strikeout++;
                }
                if (c == 'P') {
                    hitByPitch++;
                }
                if (c == 'O') {
                    outs++;
                }
                if (c == 'S') {
                    sacrifice++;
                }
            }
        }

        public int atBats() {
            return hits + outs + strikeout;
        }

        public int plateAppearances() {
            return atBats() + hitByPitch + sacrifice + walks;
        }

        public double onBasePercentage() {
            if (plateAppearances() == 0) {
                return 0;
            }
            return (hits + walks + hitByPitch) / (double) plateAppearances();
        }

        public double batAvg() {
            if (atBats() == 0) {
                return 0;
            }
            return hits / (double) atBats();
        }

        // getters and setters for name, hits, outs, strikeouts, walks, hit by pitch, sacrifice
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public int getOuts() {
            return outs;
        }

        public void setOuts(int outs) {
            this.outs = outs;
        }

        public int getStrikeout() {
            return strikeout;
        }

        public void setStrikeout(int strikeout) {
            this.strikeout = strikeout;
        }

        public int getWalks() {
            return walks;
        }

        public void setWalks(int walks) {
            this.walks = walks;
        }

        public int getHitByPitch() {
            return hitByPitch;
        }

        public void setHitByPitch(int hitByPitch) {
            this.hitByPitch = hitByPitch;
        }

        public int getSacrifice() {
            return sacrifice;
        }

        public void setSacrifice(int sacrifice) {
            this.sacrifice = sacrifice;
        }

        // created a toString method to easier format all output
        public String toString() {
            String out = "";
            out += String.format("%s\t", getName());
            out += String.format("%d\t", atBats());
            out += String.format("%d\t", getHits());
            out += String.format("%d\t", getWalks());
            out += String.format("%d\t", getStrikeout());
            out += String.format("%d\t", getHitByPitch());
            out += String.format("%d\t", getSacrifice());
            out += String.format("%.3f\t", batAvg());
            out += String.format("%.3f", onBasePercentage());
            return out;
        }
    }

    public static void leagueLeaders(LinkList list) {
        Iterator<Player> it = list.iterator();
        // created variables to hold all 6 stats needed, then created a lower variable to be a place holder
        // also created 6 different arrays to hold stats for each player
        final int MAX_SIZE = 60;
        int bA1 = 0;
        int oBP1 = 0;
        int hits1 = 0;
        int walks1 = 0;
        int strikeouts1 = 0;
        int hitByPitch1 = 0;
        int lowest;
        PlayerStat bA[] = new PlayerStat[MAX_SIZE];
        PlayerStat oBP[] = new PlayerStat[MAX_SIZE];
        PlayerStat hits[] = new PlayerStat[MAX_SIZE];
        PlayerStat walks[] = new PlayerStat[MAX_SIZE];
        PlayerStat strikeouts[] = new PlayerStat[MAX_SIZE];
        PlayerStat hitByPitch[] = new PlayerStat[MAX_SIZE];
        int bACount = 0;
        int oBP1Count = 0;
        int hits1Count = 0;
        int walks1Count = 0;
        int strikeouts1Count = 0;
        int hitByPitch1Count = 0;
        int index;

        // while loop iterates through players in order to sort the stats
        while (it.hasNext()) {
            Player player = it.next();

            //
            // batting average
            index = bACount;
            for (int i = 0; i < bACount; i++) {
                if (player.batAvg() > bA[i].getStat()) {
                    index = i;
                    break;
                }
            }
            for (int i = bACount; i > index; i--) {
                if (i < MAX_SIZE) {
                    bA[i] = bA[i - 1];
                }
            }
            if (bACount < MAX_SIZE) {
                bACount++;
            }
            bA[index] = new PlayerStat(player.getName(), player.batAvg());

            // on base percentage
            index = oBP1Count;
            for (int i = 0; i < oBP1Count; i++) {
                if (player.onBasePercentage() > oBP[i].getStat()) {
                    index = i;
                    break;
                }
            }
            for (int i = oBP1Count; i > index; i--) {
                if (i < MAX_SIZE) {
                    oBP[i] = oBP[i - 1];
                }
            }
            if (oBP1Count < MAX_SIZE) {
                oBP1Count++;
            }
            oBP[index] = new PlayerStat(player.getName(), player.onBasePercentage());

            // hits
            index = hits1Count;
            for (int i = 0; i < hits1Count; i++) {
                if (player.getHits() > hits[i].getStat()) {
                    index = i;
                    break;
                }
            }
            for (int i = hits1Count; i > index; i--) {
                if (i < MAX_SIZE) {
                    hits[i] = hits[i - 1];
                }
            }
            if (hits1Count < MAX_SIZE) {
                hits1Count++;
            }
            hits[index] = new PlayerStat(player.getName(), player.getHits());

            // walks
            index = walks1Count;
            for (int i = 0; i < walks1Count; i++) {
                if (player.getWalks() > walks[i].getStat()) {
                    index = i;
                    break;
                }
            }
            for (int i = walks1Count; i > index; i--) {
                if (i < MAX_SIZE) {
                    walks[i] = walks[i - 1];
                }
            }
            if (walks1Count < MAX_SIZE) {
                walks1Count++;
            }
            walks[index] = new PlayerStat(player.getName(), player.getWalks());


            // strikeouts
            index = strikeouts1Count;
            for (int i = 0; i < strikeouts1Count; i++) {
                if (player.getStrikeout() < strikeouts[i].getStat()) {
                    index = i;
                    break;
                }
            }
            for (int i = strikeouts1Count; i > index; i--) {
                if (i < MAX_SIZE) {
                    strikeouts[i] = strikeouts[i - 1];
                }
            }
            if (strikeouts1Count < MAX_SIZE) {
                strikeouts1Count++;
            }
            strikeouts[index] = new PlayerStat(player.getName(), player.getStrikeout());

            // hit by pitch
            index = hitByPitch1Count;
            for (int i = 0; i < hitByPitch1Count; i++) {
                if (player.getHitByPitch() > hitByPitch[i].getStat()) {
                    index = i;
                    break;
                }
            }
            for (int i = hitByPitch1Count; i > index; i--) {
                if (i < MAX_SIZE) {
                    hitByPitch[i] = hitByPitch[i - 1];
                }
            }
            if (hitByPitch1Count < MAX_SIZE) {
                hitByPitch1Count++;
            }
            hitByPitch[index] = new PlayerStat(player.getName(), player.getHitByPitch());
//=================================================================


            /*if (oBP1 < 3) {
                oBP[oBP1] = new PlayerStat(player.getName(), player.onBasePercentage());
                oBP1++;
            } else {
                lowest = 0;
                for (int i = 1; i < 3; i++) {
                    if (oBP[i].getStat() < oBP[lowest].getStat()) {
                        lowest = i;
                    }
                }
                if (player.onBasePercentage() > oBP[lowest].getStat()) {
                    oBP[lowest] = new PlayerStat(player.getName(), player.onBasePercentage());
                }

            }

            if (hits1 < 3) {
                hits[hits1] = new PlayerStat(player.getName(), player.getHits());
                hits1++;
            } else {
                lowest = 0;
                for (int i = 1; i < 3; i++) {
                    if (hits[i].getStat() < hits[lowest].getStat()) {
                        lowest = i;
                    }
                }
                if (player.hits > hits[lowest].getStat()) {
                    hits[lowest] = new PlayerStat(player.getName(), player.getHits());
                }

            }

            if (walks1 < 3) {
                walks[walks1] = new PlayerStat(player.getName(), player.getWalks());
                walks1++;
            } else {
                lowest = 0;
                for (int i = 1; i < 3; i++) {
                    if (walks[i].getStat() < walks[lowest].getStat()) {
                        lowest = i;
                    }
                }
                if (player.walks > walks[lowest].getStat()) {
                    walks[lowest] = new PlayerStat(player.getName(), player.getWalks());
                }

            }

            if (strikeouts1 < 3) {
                strikeouts[strikeouts1] = new PlayerStat(player.getName(), player.getStrikeout());
                strikeouts1++;
            } else {
                lowest = 0;
                for (int i = 1; i < 3; i++) {
                    if (strikeouts[i].getStat() > strikeouts[lowest].getStat()) {
                        lowest = i;
                    }
                }
                if (player.strikeout < strikeouts[lowest].getStat()) {
                    strikeouts[lowest] = new PlayerStat(player.getName(), player.getStrikeout());
                }

            }

            if (hitByPitch1 < 3) {
                hitByPitch[hitByPitch1] = new PlayerStat(player.getName(), player.getHitByPitch());
                hitByPitch1++;
            } else {
                lowest = 0;
                for (int i = 1; i < 3; i++) {
                    if (hitByPitch[i].getStat() < hitByPitch[lowest].getStat()) {
                        lowest = i;
                    }
                }
                if (player.hitByPitch > hitByPitch[lowest].getStat()) {
                    hitByPitch[lowest] = new PlayerStat(player.getName(), player.getHitByPitch());
                }
            }*/

 //==============================================================
        }

        /*sortPlayerStatArray(bA);
        sortPlayerStatArray(oBP);
        sortPlayerStatArray(hits);
        sortPlayerStatArray(walks);
        sortPlayerStatArrayAscending(strikeouts);
        sortPlayerStatArray(hitByPitch);
        printPlayerStatArray(bA);*/

        // Prints header "LEAGUE LEADERS" for top 3 stat values
        System.out.println("LEAGUE LEADERS");
        // limiting print output based on the previous output

        // batting average
        System.out.println("BATTING AVERAGE");
        double currentStat = bA[0].getStat();
        int i = 0;
        while (i < 3 && i < bACount) {
            System.out.printf("%.3f\t", bA[i].getStat());
            System.out.print(bA[i].getName());
            i++;
            while (i < bACount && bA[i].getStat() == currentStat) {
                System.out.print(", " + bA[i].getName());
                i++;
            }
            System.out.println();
            if (i < bACount) {
                currentStat = bA[i].getStat();
            }
        }

        // on base percentage
        System.out.println("\nON-BASE PERCENTAGE");
        currentStat = oBP[0].getStat();
        i = 0;
        while (i < 3 && i < oBP1Count) {
            System.out.printf("%.3f\t", oBP[i].getStat());
            System.out.print(oBP[i].getName());
            i++;
            while (i < oBP1Count && oBP[i].getStat() == currentStat) {
                System.out.print(", " + oBP[i].getName());
                i++;
            }
            System.out.println();
            if (i < oBP1Count) {
                currentStat = oBP[i].getStat();
            }
        }

        // hits
        System.out.println("\nHITS");
        currentStat = hits[0].getStat();
        i = 0;
        while (i < 3 && i < hits1Count) {
            System.out.printf("%.0f\t", hits[i].getStat());
            System.out.print(hits[i].getName());
            i++;
            while (i < hits1Count && hits[i].getStat() == currentStat) {
                System.out.print(", " + hits[i].getName());
                i++;
            }
            System.out.println();
            if (i < hits1Count) {
                currentStat = hits[i].getStat();
            }
        }

        // walks
        System.out.println("\nWALKS");
        currentStat = walks[0].getStat();
        i = 0;
        while (i < 3 && i < walks1Count) {
            System.out.printf("%.0f\t", walks[i].getStat());
            System.out.print(walks[i].getName());
            i++;
            while (i < walks1Count && walks[i].getStat() == currentStat) {
                System.out.print(", " + walks[i].getName());
                i++;
            }
            System.out.println();
            if (i < walks1Count) {
                currentStat = walks[i].getStat();
            }
        }

        // strikeouts
        System.out.println("\nSTRIKEOUTS");
        currentStat = strikeouts[0].getStat();
        i = 0;
        while (i < 3 && i < strikeouts1Count) {
            System.out.printf("%.0f\t", strikeouts[i].getStat());
            System.out.print(strikeouts[i].getName());
            i++;
            while (i < strikeouts1Count && strikeouts[i].getStat() == currentStat) {
                System.out.print(", " + strikeouts[i].getName());
                i++;
            }
            System.out.println();
            if (i < strikeouts1Count) {
                currentStat = strikeouts[i].getStat();
            }
        }

        // hit by pitch
        System.out.println("\nHIT BY PITCH");
        currentStat = hitByPitch[0].getStat();
        i = 0;
        while (i < 3 && i < hitByPitch1Count) {
            System.out.printf("%.0f\t", hitByPitch[i].getStat());
            System.out.print(hitByPitch[i].getName());
            i++;
            while (i < hitByPitch1Count && hitByPitch[i].getStat() == currentStat) {
                System.out.print(", " + hitByPitch[i].getName());
                i++;
            }
            System.out.println();
            if (i < hitByPitch1Count) {
                currentStat = hitByPitch[i].getStat();
            }
        }
        System.out.println();

        /*printPlayerStatArray(oBP);
        printPlayerStatArray(hits);
        printPlayerStatArray(walks);
        printPlayerStatArray(strikeouts);
        printPlayerStatArray(hitByPitch);*/

    }


    /*public static void printPlayerStatArray(PlayerStat[] array) {
        for (int i = 0; i < 12; i++) {
            System.out.print(array[i] + " \n");
        }
        System.out.println();
    }


    public static void printIntArray(int[] array) {
        for (int i = 0; i < 3; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void sortPlayerStatArray(PlayerStat[] array) {
        if (array[1].getStat() > array[0].getStat()) {
            PlayerStat temp = array[1];
            array[1] = array[0];
            array[0] = temp;
        }
        if (array[1].getStat() < array[2].getStat()) {
            PlayerStat temp = array[2];
            array[2] = array[1];
            array[1] = temp;
            if (array[1].getStat() > array[0].getStat()) {
                temp = array[1];
                array[1] = array[0];
                array[0] = temp;
            }
        }
    }


    public static void sortPlayerStatArrayAscending(PlayerStat[] array) {
        if (array[1].getStat() < array[0].getStat()) {
            PlayerStat temp = array[1];
            array[1] = array[0];
            array[0] = temp;
        }
        if (array[1].getStat() > array[2].getStat()) {
            PlayerStat temp = array[2];
            array[2] = array[1];
            array[1] = temp;
            if (array[1].getStat() < array[0].getStat()) {
                temp = array[1];
                array[1] = array[0];
                array[0] = temp;
            }
        }
    }*/


}