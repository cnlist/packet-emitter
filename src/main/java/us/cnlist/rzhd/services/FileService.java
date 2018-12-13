package us.cnlist.rzhd.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileService {

    @Value("${us.cnlist.rzhd.nscale.client.dumpDirectory}")
    private String dumpDirectory;
    private Set<File> files = new HashSet<>();

    @Scheduled(fixedDelayString = "${us.cnlist.rzhd.nscale.client.sendPacketPeriodSeconds}000")
    public void readFiles() {
        System.out.println("File storage size is " + files.size());
        File dumpDir = new File(dumpDirectory);
        if (dumpDir.exists() && dumpDir.isDirectory()) {

            synchronized (this.files) {
                for (File f : dumpDir.listFiles()) {
                    files.add(f);

                }
            }

        } else {
            System.err.println("Dump directory does not exist");
        }
    }

}
