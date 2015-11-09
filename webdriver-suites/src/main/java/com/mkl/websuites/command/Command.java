package com.mkl.websuites.command;


/**
 * Main interface for commands that can be run from scenario files.
 * <p>Use rather {@link com.mkl.websuites.command.BaseCommand} as a base class for your commands<p>
 * 
 * @author Marcin Klosinski
 *
 */
public interface Command {


    /**
     * Runs a command.
     */
    void run();
}
