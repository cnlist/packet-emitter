package us.cnlist.rzhd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileService {

    @Value("${us.cnlist.rzhd.nscale.client.dumpDirectory}")
    private String dumpDirectory;
    private Set<File> files = new HashSet<>();
    private boolean lock;
    @Autowired
    private Nscale nscale;

    @Scheduled(fixedDelayString = "${us.cnlist.rzhd.nscale.client.sendPacketPeriodSeconds}000")
    public void readFiles() {
if (!lock) {
    System.out.println("File storage size is " + files.size());
    lock=true;
    File dumpDir = new File(dumpDirectory);
    if (dumpDir.exists() && dumpDir.isDirectory()) {


        for (File f : dumpDir.listFiles()) {
            System.out.println("reading: " + f.getName());
            sendOneFile(f);

        }

    } else {
        System.err.println("Dump directory does not exist");
    }
lock = false;
}

    }


    public void writeFiles(Set<File> fls) {

            while (fls.iterator().hasNext()) {
                File nextFile = fls.iterator().next();
                if (nextFile.exists()) {
                    sendOneFile(nextFile);
                } else {

                }
            }


    }

    private void sendOneFile(File file) {


              try (FileInputStream in = new FileInputStream(file))  {


                  byte[] buffer = new byte[512];
                  while (in.read(buffer)!=-1) {
                      nscale.client().sendByteArray(buffer);
                  }

              } catch (Exception e) {
                  e.printStackTrace();
              }
          }



}
