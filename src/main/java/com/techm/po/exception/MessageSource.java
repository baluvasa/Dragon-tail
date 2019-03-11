package com.techm.po.exception;

import java.util.Locale;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;

public interface MessageSource {
    @Nullable
    String getMessage(String var1, @Nullable Object[] var2, @Nullable String var3, Locale var4);

    String getMessage(String var1, @Nullable Object[] var2, Locale var3) throws NoSuchMessageException;

    String getMessage(MessageSourceResolvable var1, Locale var2) throws NoSuchMessageException;
}

