package com.feit.feep.dbms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.feit.feep.core.Global;
import com.feit.feep.exception.json.JsonException;
import com.feit.feep.util.json.FeepJsonUtil;

public class EntityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> data;

    public EntityBean() {
        data = new HashMap<String, Object>();
    }

    public String[] getFieldNames() {
        if (!data.isEmpty()) {
            return data.keySet().toArray(new String[data.keySet().size()]);
        }
        return new String[0];
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void removeField(String key) {
        data.remove(key);
    }

    public String toString() {
        try {
            if (!data.isEmpty()) {
                return FeepJsonUtil.toJson(data);
            }
        } catch (JsonException e) {
            Global.getInstance().logError("EntityBean toString error", e);
        }
        return null;
    }

    public String toString(String dataFormat) {
        try {
            if (!data.isEmpty()) {
                return FeepJsonUtil.toJson(data, dataFormat);
            }
        } catch (JsonException e) {
            Global.getInstance().logError("EntityBean toString error", e);
        }
        return null;
    }

    public int size() {
        return this.size();
    }

    public BigDecimal getBigDecimal(String name) {
        BigDecimal bigDecimal = null;
        Object obj = get(name);
        if (null != obj) {
            if (obj instanceof BigDecimal) {
                bigDecimal = (BigDecimal) obj;
            } else if (obj instanceof String) {
                bigDecimal = new BigDecimal((String) obj);
            } else if (obj instanceof BigInteger) {
                bigDecimal = new BigDecimal((BigInteger) obj);
            } else if (obj instanceof Number) {
                bigDecimal = new BigDecimal(((Number) obj).doubleValue());
            } else {
                throw new ClassCastException("Not possible to getBigDecimal [" + obj + "] ");
            }
        }
        return bigDecimal;
    }

    public boolean getBoolean(String name) {
        boolean bool = false;
        Object obj = get(name);
        if (null != obj) {
            bool = Boolean.parseBoolean(obj.toString());
        }
        return bool;
    }

    public byte getByte(String name) {
        byte b = 0;
        Object obj = get(name);
        if (null != obj) {
            b = Byte.parseByte(obj.toString());
        }
        return b;
    }

    public Date getDate(String name) {
        Date date = null;
        Object obj = get(name);
        if (null != obj) {
            if (obj instanceof java.sql.Date) {
                date = new Date(((java.sql.Date) obj).getTime());
            } else if (obj instanceof Time) {
                date = new Date(((Time) obj).getTime());
            } else if (obj instanceof Timestamp) {
                date = new Date(((Timestamp) obj).getTime());
            } else if (obj instanceof java.util.Date) {
                date = (Date) obj;
            } else {
                throw new ClassCastException("Not possible to getDate [" + obj + "] ");
            }
        }
        return date;
    }

    public Date getDate(String name, String format) {
        Date date = null;
        Object obj = get(name);
        if (null != obj) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(obj.toString());
            } catch (Exception e) {
                Global.getInstance().logError("Not possible to getDate [" + obj + "] ,format:" + format, e);
            }
        }
        return date;
    }

    public double getDouble(String name) {
        double d = 0;
        Object obj = get(name);
        if (null != obj) {
            d = Double.parseDouble(obj.toString());
        }
        return d;
    }

    public float getFloat(String name) {
        float f = 0;
        Object obj = get(name);
        if (null != obj) {
            f = Float.parseFloat(obj.toString());
        }
        return f;
    }

    public int getInt(String name) {
        int i = 0;
        Object obj = get(name);
        if (null != obj) {
            i = Integer.parseInt(obj.toString());
        }
        return i;
    }

    public long getLong(String name) {
        long l = 0;
        Object obj = get(name);
        if (null != obj) {
            l = Long.parseLong(obj.toString());
        }
        return l;
    }

    public short getShort(String name) {
        short s = 0;
        Object obj = get(name);
        if (null != obj) {
            s = Short.parseShort(obj.toString());
        }
        return s;
    }

    public String getString(String name) {
        Object obj = get(name);
        if (null != obj) {
            return obj.toString();
        }
        return null;
    }

    Map<String, Object> getData() {
        return data;
    }
}