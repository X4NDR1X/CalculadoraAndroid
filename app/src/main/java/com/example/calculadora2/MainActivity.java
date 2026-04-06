package com.example.calculadora2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String count = "";
    TextView result;
    String equacao1 = "";
    String equacao2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setnum(View v) {
        Button botaoClicado = (Button) v;
        String numeroDoBotao = botaoClicado.getText().toString();

        if (count.equals("0") || count.isEmpty() || count.equals("Erro")) {
            count = numeroDoBotao;
        } else {
            if (count.length() < 34) {
                count += numeroDoBotao;
            }
        }

        result.setText(count);

        if (count.length() > 11) {
            result.setTextSize(40);
        }
    }

    public void clearResult(View v) {
        count = "0";
        equacao1 = "";
        equacao2 = "";
        result.setTextSize(60);
        result.setText(count);
    }

    public void eraseLast(View v) {
        if (count.equals("0") || count.isEmpty() || count.equals("Erro")) {
            count = "0";
            result.setText(count);
            return;
        }

        if (count.length() > 1) {
            count = count.substring(0, count.length() - 1);
        } else {
            count = "0";
        }

        result.setText(count);

        if (count.length() <= 11) {
            result.setTextSize(60);
        }
    }

    public void plusMinus(View v) {
        Button botaoClicado = (Button) v;
        String operacaoS = botaoClicado.getText().toString();

        // REGRA 1: Começar a conta com número negativo
        // Se a tela tá zerada e o usuário aperta "-", ele quer um número negativo!
        if (count.equals("0") || count.isEmpty() || count.equals("Erro")) {
            if (operacaoS.equals("-")) {
                count = "-";
                result.setText(count);
            }
            return; // Encerra o método aqui para não armar uma conta ainda
        }

        boolean hasSign = false;

        int inicioDaBusca = 0;
        if (count.startsWith("-")) {
            inicioDaBusca = 1;
        }

        for (int i = inicioDaBusca; i < count.length(); i++) {
            char currentCharacter = count.charAt(i);

            if (currentCharacter == '+' || currentCharacter == '-' || currentCharacter == 'x' || currentCharacter == '÷') {
                hasSign = true;
                break;
            }
        }

        if (!hasSign) {
            equacao1 = count;
            String ultimoCaractere = count.substring(count.length() - 1);

            if (!ultimoCaractere.equals("+") && !ultimoCaractere.equals("-") && !ultimoCaractere.equals("x") && !ultimoCaractere.equals("÷")) {

                if (operacaoS.equals("+")) {
                    count += "+";
                } else if (operacaoS.equals("-")) {
                    count += "-";
                } else if (operacaoS.equals("x")) {
                    count += "x";
                } else if (operacaoS.equals("÷")) {
                    count += "÷";
                }

                if (count.length() > 11) {
                    result.setTextSize(40);
                }
                result.setText(count);
            }
        } else {
            // REGRA 2: Permitir número negativo na segunda parte da equação
            // Se a gente já tem um sinal na conta (ex: 5x), deixamos colocar um "-" logo depois
            String ultimoCaractere = count.substring(count.length() - 1);

            if ((ultimoCaractere.equals("x") || ultimoCaractere.equals("÷")) && operacaoS.equals("-")) {
                count += "-";

                if (count.length() > 11) {
                    result.setTextSize(40);
                }
                result.setText(count);
            }
        }
    }

    public void calc(View v) {
        if (equacao1.isEmpty() || count.equals("Erro")) {
            return;
        }

        if (count.length() <= equacao1.length() + 1) {
            return;
        }

        equacao2 = count.substring((equacao1.length() + 1), count.length());
        String sinal = count.substring(equacao1.length(), equacao1.length() + 1);

        int equacao1Int = Integer.parseInt(equacao1);
        int equacao2Int = Integer.parseInt(equacao2);

        equacao1 = "";
        equacao2 = "";

        if (sinal.equals("+")) {
            count = String.valueOf((equacao1Int + equacao2Int));
        } else if (sinal.equals("-")) {
            count = String.valueOf((equacao1Int - equacao2Int));
        } else if (sinal.equals("x")) {
            count = String.valueOf((equacao1Int * equacao2Int));
        } else if (sinal.equals("÷")) {
            // Proteção contra divisão por zero!
            if (equacao2Int == 0) {
                count = "Erro";
            } else {
                count = String.valueOf((equacao1Int / equacao2Int));
            }
        }

        result.setText(count);

        if (count.length() <= 11) {
            result.setTextSize(60);
        }
    }

    public void bhaskara (View v){ // metodo para abrir a tela para calcular bhaskara
        count = "0";
        equacao1 = "";
        equacao2 = "";
        result.setText(count);
        result.setTextSize(60);
        // zera totalmente a calculadora principal

        Intent bhaskaraCall = new Intent(MainActivity.this, bhaskaraMain.class); //utiliza da intenção (intent) para demonstrar em qual classe estamos agora e para qual classe vamos

        startActivity(bhaskaraCall);
    }
}