package ru.catstack.fx_engine.impl;

import ru.catstack.fx_engine.resources.EngineConfig;

public interface GApplication {
    EngineConfig config = new EngineConfig();
    void onStart() throws Exception;
}
