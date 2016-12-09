package nju.quadra.hms;

import nju.quadra.hms.net.HttpService;

/**
 * Created by adn55 on 2016/11/22.
 */
class ServerRunner {
    public static void main(String[] args) {
        HttpService httpService = new HttpService(8081);
        try {
            httpService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
