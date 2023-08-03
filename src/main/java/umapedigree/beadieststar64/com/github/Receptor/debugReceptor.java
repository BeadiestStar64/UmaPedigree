package umapedigree.beadieststar64.com.github.Receptor;

import umapedigree.beadieststar64.com.github.UmaPedigree;

import java.util.Scanner;

public class debugReceptor extends UmaPedigree {

    public void acceptDebug() {
        Scanner scanner = new Scanner(System.in);
        if(scanner.next().equalsIgnoreCase("debug warning accept")) {

        }else{
            getDisplay().welcome();
        }
    }

}
