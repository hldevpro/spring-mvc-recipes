package ru.alkv.springrecipes.recipes.rcp_2_1_1;

public interface SequenceDao {
	Sequence getSequence(String sequenceId);

	int getNextValue(String sequenceId);
}
