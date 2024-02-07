package com.dataforge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.dataforge.model.CPF;

@Service
public class CPFService {

	public CPF generateSingleCPF() {
		return new CPF(generateRandomCPF());
	}

	public List<CPF> generateMultipleCPF(int quantity) {
		List<CPF> cpfs = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			cpfs.add(new CPF(generateRandomCPF()));
		}
		return cpfs;
	}

	private String generateRandomCPF() {
	    Random random = new Random();
	    String cpf;
	    boolean isValid = false;

	    do {
	        int[] cpfArray = new int[9];

	        // Gera os 9 primeiros dígitos do CPF
	        for (int i = 0; i < 9; i++) {
	            cpfArray[i] = random.nextInt(10);
	        }

	        // Calcula o primeiro dígito verificador do CPF
	        int firstVerifierDigit = calculateVerifierDigit(cpfArray, 10);
	        cpfArray = appendToArray(cpfArray, firstVerifierDigit); // Adiciona o primeiro dígito verificador ao array

	        // Calcula o segundo dígito verificador do CPF
	        int secondVerifierDigit = calculateVerifierDigit(cpfArray, 11);
	        cpfArray = appendToArray(cpfArray, secondVerifierDigit); // Adiciona o segundo dígito verificador ao array

	        // Formata o CPF para exibição
	        StringBuilder cpfBuilder = new StringBuilder();
	        for (int i = 0; i < 11; i++) {
	            if (i == 3 || i == 6) {
	                cpfBuilder.append(".");
	            } else if (i == 9) {
	                cpfBuilder.append("-");
	            }
	            cpfBuilder.append(cpfArray[i]);
	        }

	        cpf = cpfBuilder.toString();
	        
	        // Valida o CPF gerado
	        isValid = validateCPF(cpf);
	    } while (!isValid);

	    return cpf;
	}


	// Método para adicionar um elemento a um array
	private int[] appendToArray(int[] array, int element) {
		int[] newArray = new int[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[array.length] = element;
		return newArray;
	}

	private int calculateVerifierDigit(int[] cpfArray, int factor) {
		int sum = 0;
		for (int i = 0; i < cpfArray.length; i++) {
			sum += cpfArray[i] * factor--;
		}
		int remainder = sum % 11;
		return (remainder < 2) ? 0 : (11 - remainder);
	}

	public boolean validateCPF(String cpf) {
		// Remove caracteres não numéricos do CPF
		cpf = cpf.replaceAll("\\D", "");

		// Verifica se o CPF possui 11 dígitos após a remoção dos caracteres não
		// numéricos
		if (cpf.length() != 11) {
			return false;
		}

		// Extrai os nove primeiros dígitos do CPF como uma string
		String cpfDigits = cpf.substring(0, 9);

		// Converte a string de dígitos do CPF em um array de inteiros
		int[] cpfArray = new int[9];
		for (int i = 0; i < 9; i++) {
			cpfArray[i] = Character.getNumericValue(cpfDigits.charAt(i));
		}

		// Calcula o primeiro dígito verificador do CPF
		int firstVerifierDigit = calculateVerifierDigit(cpfArray, 10);

		// Adiciona o primeiro dígito verificador ao array
		cpfArray = appendToArray(cpfArray, firstVerifierDigit);

		// Calcula o segundo dígito verificador do CPF
		int secondVerifierDigit = calculateVerifierDigit(cpfArray, 11);

		// Adiciona o segundo dígito verificador ao array
		cpfArray = appendToArray(cpfArray, secondVerifierDigit);

		// Cria uma string representando o CPF completo
		StringBuilder fullCPF = new StringBuilder();
		for (int digit : cpfArray) {
			fullCPF.append(digit);
		}

		// Verifica se o CPF gerado é igual ao CPF recebido como entrada
		return fullCPF.toString().equals(cpf);
	}

}
