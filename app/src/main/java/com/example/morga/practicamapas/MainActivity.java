package com.example.morga.practicamapas;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback{

    ListView lstMonumentos;

    //Fragment per codi del tipus Mapa!.
    SupportMapFragment sM;

    private Marker marcador;
    double latitut = 0.0;
    double longitud = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Voy a añadir los items de la lista
        Monumento[] datos = new Monumento[] {
                new Monumento("Casa de caridad de Barcelona", "La casa de caridad es una institución de caridad que se hallaba situada en la calle de Montealegre de Barcelona. En la actualidad parte del edificio lo ocupa el Centro de Cultura Contemporánea de Barcelona (CCCB) que acoge exposiciones fijas dedicadas a diversos estilos artísticos que van desde la fotografía a la escultura, pasando por la pintura o los frescos." , "casa_caridad"),
                new Monumento("Convento de los Ángeles", "El Convento de los Ángeles (en catalán: Convent dels Àngels) es una edificación concebida como convento para monjas dominicas en la ciudad española de Barcelona. Ubicado en el carrer dels Àngels, número 3, sirve en la actualidad como espacio para actividades culturales, formado parte del conjunto del Museo de Arte Contemporáneo de Barcelona (MACBA). En 1993, partes del mismo fueron declarados Bien de Interés Cultural (BIC) y Bien Cultural de Interés Nacional de Cataluña." , "convento_angeles"),
                new Monumento("Capilla de Sant Llàtzer", "La Capilla de Sant Llàtzer es una de las pocas construcciones románicas que se conservan en la ciudad condal." , "iglesia_llatzer"),
                new Monumento("Hospital Santa Cruz", "El Hospital de la Santa Cruz es un edificio gótico del siglo XV, situado en la ciudad de Barcelona." , "santa_creu"),
                new Monumento("Teatro del Raval", "El Teatro del Raval es un espacio teatral situado dentro de los espacios parroquiales de la Iglesia de Nuestra Señora de Carmen, en la calle de San Antonio Abad 12, del barrio del Raval de Barcelona." , "teatro_raval"),
                new Monumento("Casa de la Ciudad", "La Casa de la Ciudad de Barcelona, que es el edificio y sede del Ayuntamiento de Barcelona, se encuentra en el centro histórico de la ciudad, enfrente del Palacio de la Generalidad de Cataluña." , "ciutat_vella"),
                new Monumento("Casa Comalat", "La casa Comalat es una obra modernista de l'Eixample de Barcelona, obra de 1909-1911 de l'arquitecte Salvador Valeri i Pupurull." , "modernismo"),
        };

        MiAdapter adapterMonumentos = new MiAdapter(this, datos);

        lstMonumentos = (ListView) findViewById(R.id.listViewMonumento);

        lstMonumentos.setAdapter(adapterMonumentos);

        //Este metodo es para saber en que item de la lista han clicado y muestra un toast montrandolo
        lstMonumentos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {


                
            }
        });


        //Instanciem un Fragment
        sM=SupportMapFragment.newInstance();

        sM.getMapAsync(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        android.support.v4.app.FragmentManager fM= getSupportFragmentManager();

        if(sM.isAdded())  fM.beginTransaction().hide(sM).commit();


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lista) {

        } else if (id == R.id.nav_map) {
            // Handle the camera action
            if(!sM.isAdded()) {
                fM.beginTransaction().add(R.id.map,sM).commit();
            }
            else {
                fM.beginTransaction().show(sM).commit();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Marker afegirMarcadorMapa(double lat, double lng,String texte,GoogleMap mMap) {
        LatLng coordenades = new LatLng(lat, lng);

        // if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenades)
                .title(texte)
                .icon(BitmapDescriptorFactory.fromResource((R.mipmap.pointer))));

        return (marcador);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Marker[] llM=new Marker[7];
        llM[0]= afegirMarcadorMapa(41.383885,2.164587,"Casa de la caridad",googleMap);
        llM[1]= afegirMarcadorMapa(41.3827189,2.1652929,"Convento de los angeles",googleMap);
        llM[2]= afegirMarcadorMapa(41.3796194,2.1641381,"Iglesia Sant Llàtzer",googleMap);
        llM[3]= afegirMarcadorMapa(41.4125087,2.1721466,"Hospital Santa Cruz",googleMap);
        llM[4]= afegirMarcadorMapa(41.3793014,2.162835,"Tetro Raval",googleMap);
        llM[5]= afegirMarcadorMapa(41.3838719,2.1685927,"Consell Municipal del Districte de Ciutat Vella",googleMap);
        llM[6]= afegirMarcadorMapa(41.3971039,2.1585535,"Casa Comalat",googleMap);

        llM[6].setDraggable(true);

        CameraUpdate mevaUbicacio = CameraUpdateFactory.newLatLngZoom(new LatLng(llM[0].getPosition().latitude,llM[0].getPosition().longitude), 15);
        googleMap.animateCamera(mevaUbicacio);

    }



    //*****
    //Metodo que hace de adapter
    //*****
    public class MiAdapter extends ArrayAdapter<Monumento> {

        // Creamos una array de tipo Titular
        Monumento[] datos;

        public MiAdapter(Context context, Monumento[] datos) {
            //Lo primero que encontramos es el constructor
            // para nuestro adaptador, al que sólo pasaremos el contexto (que normalmente
            // será la actividad desde la que se crea el adaptador) y el array de datos a
            // mostrar, que en nuestro caso es un array de objetos de tipo Titular. En este
            // constructor tan sólo llamaremos al constructor padre tal como ya vimos al
            // principio de este artículo, pasándole nuestros dos parámetros (contexto y
            // datos) y el ID del layout que queremos utilizar (en nuestro caso el nuevo
            // que hemos creado, listitem_titular).
            super(context, R.layout.lista_monumento, datos);
            this.datos = datos;
        }

        //Redefinimos el método encargado de generar y rellenar con nuestros datos todos
        // los controles necesarios de la interfaz gráfica de cada elemento de la lista.
        // Este método es getView().
        // El método getView() se llamará cada vez que haya que
        // mostrar un elemento de la lista.
        public View getView(int position, View convertView, ViewGroup parent) {
            // Lo primero que debe hacer es “inflar” el layout XML que hemos creado.
            // Esto consiste en consultar el XML de nuestro layout y crear e inicializar
            // la estructura de objetos java equivalente. Para ello, crearemos un nuevo
            // objeto LayoutInflater y generaremos la estructura de objetos mediante su
            // método inflate(id_layout).
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.lista_monumento, null);


            // Tras esto, tan sólo tendremos que obtener la referencia a cada una
            // de nuestras etiquetas como siempre lo hemos hecho y asignar su texto
            // correspondiente según los datos de nuestro array y la posición del
            // elemento actual (parámetro position del método getView()).
            TextView lblTitulo = (TextView) item.findViewById(R.id.textViewTitulo);
            lblTitulo.setText(datos[position].getTitulo());

            TextView lblSubtitulo = (TextView) item.findViewById(R.id.textViewDescripcion);
            lblSubtitulo.setText(datos[position].getSubtitulo());

            //Imagen
            ImageView imagen = (ImageView) item.findViewById(R.id.imageViewMonumento);

            String nombre=(datos[position].getImagen());

            switch (nombre) {
                case "casa_caridad":
                    imagen.setImageResource(R.drawable.casa_caridad);
                    break;

                case "ciutat_vella":
                    imagen.setImageResource(R.drawable.ciutat_vella);
                    break;

                case "convento_angeles":
                    imagen.setImageResource(R.drawable.convento_angeles);
                    break;

                case "iglesia_llatzer":
                    imagen.setImageResource(R.drawable.iglesia_llatzer);
                    break;

                case "modernismo":
                    imagen.setImageResource(R.drawable.modernismo);
                    break;

                case "santa_creu":
                    imagen.setImageResource(R.drawable.santa_creu);
                    break;

                case "teatro_raval":
                    imagen.setImageResource(R.drawable.teatro_raval);
                    break;

            }

            return (item);
        }
    }
}

