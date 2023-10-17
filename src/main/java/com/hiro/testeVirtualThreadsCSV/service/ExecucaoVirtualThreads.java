package com.hiro.testeVirtualThreadsCSV.service;

import com.hiro.testeVirtualThreadsCSV.processors.Processor;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ExecucaoVirtualThreads {

//    static final String CSVFILE = "C:/Users/gabri/Downloads/TesteVirtualThreadsSpringCSV.CSV";

//    @Async
//    public void execucaoVTComCSV() {
//
//        long tempoInicio = System.currentTimeMillis();
//
//        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
//
//            try (CSVReader reader = new CSVReader(new FileReader(CSVFILE))) {
//
//                String[] line;
//                int processoId = 0;
//
//                while((line = reader.readNext()) != null) {
//
//                    for(String cell : line) {
//
//                        var processo = new Processor().executarCSV(cell, processoId);
//                        executorService.submit(processo);
//
//                        processoId++;
//
//                    }
//
//                    System.out.println();
//
//                }
//
//            } catch (IOException | CsvException e) {
//
//                throw new RuntimeException(e);
//
//            }
//
//        }
//
//        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + "s");
//        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toMillis() + "ms");
//
//    }

    @Async
    public void execucaoVirtualThreads() {

        long tempoInicio = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            for(int i = 0; i < 10000; i++) {

                var processo = new Processor().executar(i);
                executorService.submit(processo);

            }

        }

        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + " segundos");

    }

    @Async
    public void execucaoNormal() {

        long tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {

            new Processor().executar(i).run();

        }

        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + " segundos");

    }

    @Async
    public void execucaoThreadPool() {

        long tempoInicio = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newFixedThreadPool(10000)) {

            for (int i = 0; i < 10000; i++) {
                var processo = new Processor().executar(i);
                executorService.submit(processo);
            }

        }

        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + " segundos");

    }

}
