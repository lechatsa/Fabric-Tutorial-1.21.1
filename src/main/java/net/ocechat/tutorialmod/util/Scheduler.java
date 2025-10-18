package net.ocechat.tutorialmod.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private static final List<Task> tasks = new ArrayList<>();

    public static void schedule(int delay, Runnable action) {
        tasks.add(new Task(action, delay));
    }

    public static void tick() {
        tasks.removeIf(Task::tick);
    }

    private static class Task {
        Runnable action;
        int remainingTicks;

        Task(Runnable action, int delay) {
            this.action = action;
            this.remainingTicks = delay;
        }

        boolean tick() {
            if (--remainingTicks <= 0) {
                action.run();
                return true;
            }
            return false;
        }
    }

    public static void registerScheduler() {
        ServerTickEvents.END_SERVER_TICK.register((server) -> {

            Scheduler.tick();

        });
    }

}
