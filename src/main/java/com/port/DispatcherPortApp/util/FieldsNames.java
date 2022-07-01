package com.port.DispatcherPortApp.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FieldsNames {
    public static Map<String, String> fieldsNames() {
        Map<String, String> res = new LinkedHashMap<>();
        res.put("Номер машины", "carNumber");
        res.put("Номер прицепа", "trailerNumber");
        res.put("Номер вод. удостоверения", "driverLicenseNumber");
        res.put("Номеклатура", "nomenclature");
        res.put("Отправитель", "sender");
        res.put("Тип", "vehicleType");
        res.put("Номер телефона", "phoneNumber");
        res.put("ФИО", "fullName");
        res.put("Дата создания", "dateOfCreation");
        res.put("Заехал", "isCome");

        return res;
    }
}
