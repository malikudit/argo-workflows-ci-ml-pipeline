package com.udit.crudapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;

public class WorkflowManager {

    public static void main(String[] args) throws Exception {
        Scanner userInputCommand = new Scanner(System.in);

        String userCommand = null, workflowName = null, checkInput = null, finalCommand = null;
        System.out.println("Enter workflow command! Options include submit, terminate, list, and suspend.");
        userCommand = userInputCommand.nextLine();

        boolean check = true;

        ArrayList<String> workflows = new ArrayList<String>();

        while (check == true) {
            System.out.println("The selected command is: " + userCommand);

            System.out.println("Enter workflow name: ");
            workflowName = userInputCommand.nextLine();
            System.out.println("The selected workflow is: " + workflowName);
            workflows.add(workflowName);

            System.out.println("Do you want to add another workflow? y/n");
            checkInput = userInputCommand.nextLine();
            if (checkInput == "y" || checkInput == "Y") {
                check = true;
            } else {
                check = false;
            }
        }

        userInputCommand.close();

        for (int i = 0; i < workflows.size(); i++) {
            if (userCommand.equals("submit")) {
                finalCommand = "argo " + userCommand + " -n argo " + "http://localhost:8080/api/download/"
                        + workflows.get(i) + ".yml";
            } else {
                finalCommand = "argo " + userCommand + "-n argo " + workflows.get(i);
            }
            System.out.println(finalCommand);
            commandSubmit(finalCommand);
        }
    }

    public static void commandSubmit(String finalCommand) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", finalCommand);

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                System.out.println("Error! The required task could not be completed.");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void commandSubmitter(String filename) {
            filename = "argo submit" + " -n argo " + "http://localhost:8080/api/download/"
                    + filename;
   
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", filename);

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                System.out.println("Error! The required task could not be completed.");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}