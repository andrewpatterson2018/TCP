package com.andrewpatterson.ase.tcp;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class TestCaseCrossoverPolicy implements CrossoverPolicy {
    public ChromosomePair crossover(Chromosome chromosome, Chromosome chromosome1) throws MathIllegalArgumentException {
        return this.crossover((TestCaseOrderChromosome) chromosome, (TestCaseOrderChromosome) chromosome1);
    }
    public ChromosomePair crossover(TestCaseOrderChromosome first, TestCaseOrderChromosome second) throws MathIllegalArgumentException {


        if (first.getLength() != second.getLength()) {
            throw new DimensionMismatchException(second.getLength(), first.getLength());
        }

        List<TestCase> firstRep = first.representation();
        List<TestCase> secondRep = second.representation();
        List<TestCase> child1Rep = new ArrayList<TestCase>(first.getLength());
        List<TestCase> child2Rep = new ArrayList<TestCase>(first.getLength());

        // select a crossover point at random (0 and length makes no sense)
        int crossoverIndex = 1 + (GeneticAlgorithm.getRandomGenerator().nextInt(first.getLength()-2));


        //add first portions to children
        child1Rep.addAll(firstRep.subList(0, crossoverIndex-1));
        child2Rep.addAll(secondRep.subList(0, crossoverIndex-1));

        //now fill up the remaining children
        int i = 0;
        while(child1Rep.size() < first.getLength()){

            if(!child1Rep.contains(secondRep.get(i))){
                child1Rep.add(secondRep.get(i));
            }
            i++;
        }
        i=0; //reset the iterator
        while(child2Rep.size() < first.getLength()){
            if(!child2Rep.contains(firstRep.get(i))){
                child2Rep.add(firstRep.get(i));
            }
            i++;
        }
        return new ChromosomePair(new TestCaseOrderChromosome(child1Rep), new TestCaseOrderChromosome(child2Rep));
    }

}
