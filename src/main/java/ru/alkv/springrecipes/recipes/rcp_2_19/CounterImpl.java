package ru.alkv.springrecipes.recipes.rcp_2_19;

public class CounterImpl implements Counter{
    private int count = 0;

    @Override
    public void increase() {
        this.count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
