package ru.catstack.fx_engine.impl;

import ru.catstack.fx_engine.EngineConfig;

import java.io.IOException;
import java.net.URL;

public abstract class IMain {
    public URL url;
    public EngineConfig config = new EngineConfig();
    public abstract void onStart() throws IOException;
}
