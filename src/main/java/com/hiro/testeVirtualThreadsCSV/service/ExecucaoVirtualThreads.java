package com.hiro.testeVirtualThreadsCSV.service;

import com.hiro.testeVirtualThreadsCSV.processors.Processor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ExecucaoVirtualThreads {

    @Async
    public void execucaoVirtualThreads() {

        long tempoInicio = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            for(int i = 0; i < 101; i++) {

                var processo = new Processor().executar(i);
                executorService.submit(processo);

            }

        }

        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + " segundos");
        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toMillis() + " Milissegundos");

    }

    @Async
    public void execucaoNormal() {

        long tempoInicio = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {

            new Processor().executar(i).run();

        }

        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + " segundos");
        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toMillis() + " Milissegundos");

    }

    @Async
    public void execucaoThreadPool() {

        long tempoInicio = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newFixedThreadPool(1000)) {

            for (int i = 0; i < 1000; i++) {
                var processo = new Processor().executar(i);
                executorService.submit(processo);
            }

        }

        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toSeconds() + " segundos");
        System.out.println(Duration.ofMillis(System.currentTimeMillis() - tempoInicio).toMillis() + " Milissegundos");

    }

}
