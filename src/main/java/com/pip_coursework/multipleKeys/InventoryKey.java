package com.pip_coursework.multipleKeys;

import java.io.Serializable;
import java.util.Date;

public class InventoryKey implements Serializable {
    private long characterId;
    private long itemId;
    private Date timeGetting;
}
