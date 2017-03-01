package com.example.morga.practicamapas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PreguntaActivity extends AppCompatActivity {

    ImageView imgMonumento;
    TextView txtTituloMonumento;
    TextView txtPregunta;
    EditText etRespuesta;
    String nombreImagen;


    private MiDataSource bd;

    int primeraVez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        imgMonumento = (ImageView) findViewById(R.id.imageViewMonumento);
        txtTituloMonumento = (TextView) findViewById(R.id.textViewNombreMonumento);
        txtPregunta = (TextView) findViewById(R.id.textViewPregunta);
        etRespuesta = (EditText) findViewById(R.id.editTextRespuesta);

        bd = new MiDataSource(this);

        // Botones de aceptar y cancelar
        Button btnAceptar = (Button) findViewById(R.id.buttonAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AceptarCambios();
            }
        });

        // Boton cancelar
        Button btnCancel = (Button) findViewById(R.id.buttonCancelar);

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CancelarCambios();
            }
        });


        Bundle bundle = getIntent().getExtras();

        txtTituloMonumento.setText(bundle.getString("titulo"));
        nombreImagen = bundle.getString("imagen");


        // Aprobecho este switch para poner la imagen correspondiente
        // para poner tambien la pregunta que toca
        switch (nombreImagen) {
            case "casa_caridad":
                imgMonumento.setImageResource(R.drawable.casa_caridad);
                txtPregunta.setText("Què és el CCCB? Demana informació a la recepció. Busca la teva pròpia imatge al CCCB. On l’has trobat? Hi ha un mirall al sostre del vestíbul, a la dreta.");
                break;

            case "ciutat_vella":
                imgMonumento.setImageResource(R.drawable.ciutat_vella);
                txtPregunta.setText("Investiga i apunta 5 serveis que ofereix l’Ajuntament del barri? Anar a pg. 7 A més a més del Raval quins altres barris comprèn el districte de Ciutat Vella? Gòtic, Barceloneta i St Pere, Sta Caterina i la Ribera");
                break;

            case "convento_angeles":
                imgMonumento.setImageResource(R.drawable.convento_angeles);
                txtPregunta.setText("Si busques bé descobriràs el verb Ravalejar. Escriu-lo i digues on l’has trobat. Mirant la façana del museu al mur que queda a mà esquerra. Ravalejar és un verb inventat que significa passejar pel Raval i altres significats que la gent del barri li vulgui donar. Quina funció té actualment el Convent dels Àngels? Museu i Arxiu");
                break;

            case "iglesia_llatzer":
                imgMonumento.setImageResource(R.drawable.iglesia_llatzer);
                txtPregunta.setText("Quin estil arquitectònic té aquesta església? romànic Per tant, entre quins segles creus que va ser construïda? X- XII");
                break;

            case "modernismo":
                imgMonumento.setImageResource(R.drawable.modernismo);
                txtPregunta.setText("Passeja pel C/ del Carme i intenta identificar un edifici Modernista. Apunta l’adreça. Anar a pg.7 Quines característiques t’han fet pensar que es tracta d’un edifici d’aquest estil?");
                break;

            case "santa_creu":
                imgMonumento.setImageResource(R.drawable.santa_creu);
                txtPregunta.setText("Què deu voler dir casa de Convalescència? Lloc on es recuperaven els malalts que ja havien estat atesos a l’Hospital abans de poder tornar a casa. Escriu les lletres que trobem a l’entrada sota l’escut: HVGVET");
                break;

            case "teatro_raval":
                imgMonumento.setImageResource(R.drawable.teatro_raval);
                txtPregunta.setText("Quina obra s’està representant actualment?");
                break;

        }



        CargarDatos();
    }

    //**********
    //Funcion que carga los datos
    //**********
    private void CargarDatos()
    {

        // Demanem un cursor que retorna un sol registre amb les dades de la tasca
        // Això es podria fer amb un classe pero...
        Cursor datos = bd.Respuesta(nombreImagen);
        datos.moveToFirst();

        // Carreguem les dades en la interfície

        etRespuesta.setText(datos.getString(datos.getColumnIndex(MiDataSource.RESPUESTA_RESPUESTA)));


    }



    //**********
    //Funcion CREAR O ACTUALIZAR los datos
    //**********
    private void AceptarCambios() {

        String respuesta = etRespuesta.getText().toString();
        // Validem les dades
        // Mirem si estem creant o estem guardant

        bd.Update(nombreImagen,respuesta);

        finish();

    }

    //**********
    //Funcion CANCELAR
    //**********
    private void CancelarCambios() {


        finish();
    }
}
