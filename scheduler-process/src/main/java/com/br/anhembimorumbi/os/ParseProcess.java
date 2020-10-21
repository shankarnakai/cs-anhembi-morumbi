package com.br.anhembimorumbi.os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.br.anhembimorumbi.os.exceptions.process.IllegalIOTimeException;
import com.br.anhembimorumbi.os.exceptions.IllegalIOTimeEnqueeException;
import com.br.anhembimorumbi.os.exceptions.process.RepeatedProcessEnqueueException;

public class ParseProcess {

    public static IProcessQueue parse(InputStream input)
            throws IOException, RepeatedProcessEnqueueException, IllegalIOTimeException, IllegalIOTimeEnqueeException {
        BufferedReader reader = null;
        IProcessQueue list = new ProcessQueue();

        try {
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                list.enqueue(parseLine(line));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        list.sort();
        return list;
    }

    public static IProcess parseLine(String line) throws IllegalIOTimeException, IllegalIOTimeEnqueeException {
        if (line != null && line.isEmpty()) {
            return null;
        }

        String[] args = line.split(" ");
        if(args.length < 3) {
            //THROW ERROR
        }

        long pid = Long.parseLong(args[0].replaceAll("[^0-9]", ""), 10);
        int duration = Integer.parseInt(args[1], 10);
        long arrive = Long.parseLong(args[2], 10);
        long[] ioTimes = null;
        if(args.length > 3) {
            ioTimes = parseIO(args[3]);
        }

        if (pid <= 0) {
            //THROW ERROR
        }

        if (duration <= 0) {
            //THROW ERROR
        }

        if (arrive <= 0) {
            //THROW ERROR
        }

        IProcess proc = new Process(pid, duration, arrive);
        if (ioTimes != null && ioTimes.length > 0) {
            for (int i = 0; i < ioTimes.length; i += 1) {
                proc.addIOTimes(ioTimes[i]);
            }
        }

        return proc;
    }

    public static long[] parseIO(String line) {
        if (line != null && line.isEmpty()) {
            return null;
        }
        String[] ioArgs = line.split(",");
        long[] io = new long[ioArgs.length];

        if(ioArgs.length > 0) {
            for (int i = 0; i < ioArgs.length; i += 1) {
                long time = Long.parseLong(ioArgs[i], 10);
                if (time <= 0) {
                    //THROW ERROR
                }
                io[i] = time;
            }
        }

        return io;
    }
}