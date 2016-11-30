package nju.quadra.hms.net;

import com.google.gson.Gson;
import nju.quadra.hms.blservice.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by adn55 on 2016/11/30.
 */
public class BLServiceFactory {

    private static InvocationHandler invocationHandler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String className = method.getDeclaringClass().getSimpleName().replace("Service", "");
            String methodName = method.getName();
            String payload = new Gson().toJson(args);
            String response = HttpClient.post("/" + className + "/" + methodName, payload);
            return new Gson().fromJson(response, method.getGenericReturnType());
        }
    };

    public static CreditRecordBLService getCreditRecordBLService() {
        return (CreditRecordBLService) Proxy.newProxyInstance(CreditRecordBLService.class.getClassLoader(),
                new Class[]{CreditRecordBLService.class}, invocationHandler);
    }

    public static CustomerBLService getCustomerBLService() {
        return (CustomerBLService) Proxy.newProxyInstance(CustomerBLService.class.getClassLoader(),
                new Class[]{CustomerBLService.class}, invocationHandler);
    }

    public static HotelBLService getHotelBLService() {
        return (HotelBLService) Proxy.newProxyInstance(HotelBLService.class.getClassLoader(),
                new Class[]{HotelBLService.class}, invocationHandler);
    }

    public static HotelPromotionBLService getHotelPromotionBLService() {
        return (HotelPromotionBLService) Proxy.newProxyInstance(HotelPromotionBLService.class.getClassLoader(),
                new Class[]{HotelPromotionBLService.class}, invocationHandler);
    }

    public static HotelRoomBLService getHotelRoomBLService() {
        return (HotelRoomBLService) Proxy.newProxyInstance(HotelRoomBLService.class.getClassLoader(),
                new Class[]{HotelRoomBLService.class}, invocationHandler);
    }

    public static OrderBLService getOrderBLService() {
        return (OrderBLService) Proxy.newProxyInstance(OrderBLService.class.getClassLoader(),
                new Class[]{OrderBLService.class}, invocationHandler);
    }

    public static UserBLService getUserBLService() {
        return (UserBLService) Proxy.newProxyInstance(UserBLService.class.getClassLoader(),
                new Class[]{UserBLService.class}, invocationHandler);
    }

    public static WebsitePromotionBLService getWebsitePromotionBLService() {
        return (WebsitePromotionBLService) Proxy.newProxyInstance(WebsitePromotionBLService.class.getClassLoader(),
                new Class[]{WebsitePromotionBLService.class}, invocationHandler);
    }

}
