package demo_exam.v1_302;

import demo_exam.v2_305.n1.Events;
import demo_exam.v2_305.n1.Person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Polinom {

    private MyDoubLinkList<Element> polinomList = new MyDoubLinkList<>();

    public Polinom(String filename) {
        doPolinom();
    }

    public void doPolinom() {
        try (BufferedReader brp = new BufferedReader(new InputStreamReader(new FileInputStream("polinom_filename"), StandardCharsets.UTF_8))) {
            String line;

            while ((line = brp.readLine()) != null) {
                String[] data = line.split(" ");
                this.polinomList.add(new Element(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(int coef, int degX, int degY) {
        for (int i = 0; i < polinomList.getSize(); i++) {
            if (polinomList.get(i).getDegX() == degX && polinomList.get(i).getDegY() == degY) {
                polinomList.add(new Element(coef + polinomList.get(i).getCoef(), degX, degY));
                polinomList.delete(i);
            } else {
                polinomList.add(new Element(coef, degX, degY));
            }
        }
    }

    public void delete(int degX, int degY) {
        for (int i = 0; i < polinomList.getSize(); i++) {
            if (polinomList.get(i).getDegX() == degX && polinomList.get(i).getDegY() == degY) {
                polinomList.delete(i);
            }
        }
    }

    public void mult(Polinom p) {
        Element multEl = p.polinomList.get(0);

        for (int i = 0; i < polinomList.getSize(); i++) {
            Element curEl = polinomList.get(i);
            Element newEl = new Element(curEl.getCoef() * multEl.getCoef(), curEl.getDegX() + multEl.getDegX(), curEl.getDegY() + multEl.getDegY());
            polinomList.set(i, newEl);
        }
    }

    public void derivate(int i) {
        if (i == 0) {
            for (int j = 0; j < polinomList.getSize(); j++) {
                polinomList.set(i, proizvodX(polinomList.get(i)));
            }
        }
        else if (i == 1) {
            for (int j = 0; j < polinomList.getSize(); j++) {
                polinomList.set(i, proizvodY(polinomList.get(i)));
            }
        } else {
            System.out.println("No more");
        }
    }

    private Element proizvodX(Element el) {
        int newCoef = el.getCoef() * el.getDegX();
        int newDegX = el.getDegX() - 1;
        Element proizEl = new Element(newCoef, newDegX, el.getDegY());
        return proizEl;
    }

    private Element proizvodY(Element el) {
        int newCoef = el.getCoef() * el.getDegX();
        int newDegY = el.getDegY() - 1;
        Element proizEl = new Element(newCoef, el.getDegX(), newDegY);
        return proizEl;
    }

    public int value(int x, int y) {
        int value = 0;
        for (int i = 0; i < polinomList.getSize(); i++) {
            Element currEl = polinomList.get(0);
            value += currEl.getCoef() * Math.pow(x, currEl.getDegX()) * Math.pow(y, currEl.getDegY());
        }
        return value;
    }

    public String toString() {
        return polinomList.toString();
    }
}
