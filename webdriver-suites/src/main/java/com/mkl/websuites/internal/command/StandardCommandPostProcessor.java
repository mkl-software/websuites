package com.mkl.websuites.internal.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.command.impl.flow.ControlFlowHandler;
import com.mkl.websuites.internal.command.impl.flow.EndControlFlowHandler;

public class StandardCommandPostProcessor implements CommandPostProcessor {


    private static StandardCommandPostProcessor instance = new StandardCommandPostProcessor();


    public static StandardCommandPostProcessor getInstance() {
        return instance;
    }



    @Override
    public List<Command> postProcessCommands(List<Command> parsedCommands) {

        List<Command> processedCommands = processControlHandlers(parsedCommands);

        return processedCommands;
    }



    protected List<Command> processControlHandlers(List<Command> originalCommandList) {

        /*
         * Algorithm:
         */

        checkMaxNestingDepths(originalCommandList);

        List<Command> outputFoldedList = new ArrayList<>();
        List<Command> elementsToRemoveFromOutputList = new ArrayList<>();

        Stack<List<Command>> nestedCommands = new Stack<>();
        nestedCommands.push(outputFoldedList);

        List<Command> currentLevel = outputFoldedList;


        for (Command command : originalCommandList) {

            if (command instanceof ControlFlowHandler) {

                if (command instanceof EndControlFlowHandler) {

                    nestedCommands.pop();
                    currentLevel = nestedCommands.peek();

                } else {
                    currentLevel.add(command);
                    currentLevel = ((ControlFlowHandler) command).getNestedCommands();
                    nestedCommands.push(currentLevel);
                }
            } else {

                currentLevel.add(command);
                // if nested mark to remove from first level:
                if (currentLevel != outputFoldedList) {

                    elementsToRemoveFromOutputList.add(command);
                }
            }
        }

        List<Command> processedWithControlHandlers = new ArrayList<>();

        for (Command command : outputFoldedList) {

            if (!elementsToRemoveFromOutputList.contains(command)) {

                processedWithControlHandlers.add(command);
            }
        }

        return processedWithControlHandlers;
    }



    private int checkMaxNestingDepths(List<Command> parsedCommands) {

        int depth = 0;
        int maxDepth = 0;
        for (Command command : parsedCommands) {

            if (command instanceof ControlFlowHandler) {
                if (command instanceof EndControlFlowHandler) {
                    depth--;
                } else {
                    depth++;
                    maxDepth = depth;
                }
            }
        }

        if (depth != 0) {

            throw new WebSuitesException("Error in control flow statements - please check if "
                    + "the control flow blocks (\"repeat\", \"if\", \"subtest\" etc.) are properly "
                    + "closed by \"end\" statements");
        }

        return maxDepth;

    }



}
