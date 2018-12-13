package us.cnlist.rzhd.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import us.cnlist.rzhd.components.NscaleClient;

@Service
public class Nscale {

    @Value("${us.cnlist.rzhd.nscale.client.port}")
    private int port;
    @Value("${us.cnlist.rzhd.nscale.client.host}")
    private String host;
    @Value("${us.cnlist.rzhd.nscale.client.sendPacketPeriodSeconds}")
    private int sendPacketPeriodSeconds;
    @Value("${us.cnlist.rzhd.nscale.client.dumpDirectory}")
    private String dumpDirectory;

    private NscaleClient nscaleClient;

    @Bean
    public NscaleClient client() {
        if (nscaleClient == null) {
            nscaleClient = new NscaleClient(host, port);

        }


        return nscaleClient;
    }


}
