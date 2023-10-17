package com.hiro.testeVirtualThreadsCSV.controller;

import com.hiro.testeVirtualThreadsCSV.service.ExecucaoVirtualThreads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ImportCSVDataThreadsController {

    @Autowired
    private ExecucaoVirtualThreads execucaoVirtualThreads;

    @GetMapping("/virtualThreads")
    public void importCSV() {

        execucaoVirtualThreads.execucaoVirtualThreads();

    }

    @GetMapping("/normal")
    public void execucaoNormal() {

        execucaoVirtualThreads.execucaoNormal();

    }

    @GetMapping("/threadPool")
    public void execucaoThreadPool() {

        execucaoVirtualThreads.execucaoThreadPool();

    }

}
