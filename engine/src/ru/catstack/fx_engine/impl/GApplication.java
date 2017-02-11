package ru.catstack.fx_engine.impl;

import ru.catstack.fx_engine.resources.EngineConfig;

public abstract class GApplication {
    public EngineConfig config = new EngineConfig();
    public abstract void onStart() throws Exception;
}
