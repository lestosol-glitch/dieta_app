import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class Dieta_App extends Activity {

    // ===================== ESTADO GLOBAL =====================
    // Leandro
    String carbCafeL = "";
    String protCafeL = "";
    String carbAlmL  = "";
    String protAlmL  = "";
    String lancheL   = "";

    // Débora
    String carbCafeD    = "";
    String protCafeD    = "";
    String carbAlmD     = "";
    String protAlmD     = "";
    String lancheD      = "";
    String carbJantarD  = "";
    String protJantarD  = "";

    // Cores
    static final int COR_PRIMARIA   = Color.parseColor("#2E7D32"); // verde escuro
    static final int COR_SECUNDARIA = Color.parseColor("#A5D6A7"); // verde claro
    static final int COR_FUNDO      = Color.parseColor("#F1F8E9"); // fundo suave
    static final int COR_TEXTO      = Color.parseColor("#1B5E20");
    static final int COR_BRANCO     = Color.WHITE;
    static final int COR_DIVISOR    = Color.parseColor("#C8E6C9");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ScrollView raiz
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(COR_FUNDO);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(0, 0, 0, 40);

        // ===== CABEÇALHO =====
        LinearLayout header = new LinearLayout(this);
        header.setBackgroundColor(COR_PRIMARIA);
        header.setPadding(32, 48, 32, 32);
        header.setOrientation(LinearLayout.VERTICAL);

        TextView titulo = new TextView(this);
        titulo.setText("🥗 Dieta Debby & Di Caprio");
        titulo.setTextColor(COR_BRANCO);
        titulo.setTextSize(22);
        titulo.setTypeface(null, Typeface.BOLD);
        titulo.setGravity(Gravity.CENTER);
        header.addView(titulo);

        TextView subtitulo = new TextView(this);
        subtitulo.setText("Selecione as opções e veja a lista de compras");
        subtitulo.setTextColor(COR_SECUNDARIA);
        subtitulo.setTextSize(13);
        subtitulo.setGravity(Gravity.CENTER);
        subtitulo.setPadding(0, 8, 0, 0);
        header.addView(subtitulo);

        root.addView(header);

        // ===== TABS =====
        TabHost tabHost = new TabHost(this);
        tabHost.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout tabLayout = new LinearLayout(this);
        tabLayout.setOrientation(LinearLayout.VERTICAL);

        TabWidget tabWidget = new TabWidget(this);
        tabWidget.setId(android.R.id.tabs);

        FrameLayout tabContent = new FrameLayout(this);
        tabContent.setId(android.R.id.tabcontent);

        tabLayout.addView(tabWidget);
        tabLayout.addView(tabContent);
        tabHost.addView(tabLayout);
        tabHost.setup();

        // ===== ABA LEANDRO =====
        ScrollView scrollL = new ScrollView(this);
        LinearLayout abaLeandro = new LinearLayout(this);
        abaLeandro.setOrientation(LinearLayout.VERTICAL);
        abaLeandro.setPadding(24, 24, 24, 24);

        abaLeandro.addView(secao("☀️ Café da Manhã × 5", this));
        abaLeandro.addView(itemFixo("✔ 1 fatia de mussarela (fixo)", this));

        abaLeandro.addView(subTitulo("Carboidrato:", this));
        String[] carbCafeOpL = {
            "3 fatias de pão integral",
            "1 pão francês + 1 pera",
            "55g de tapioca",
            "0,5 maçã + 1 banana + 1 iogurte desnatado"
        };
        RadioGroup rgCarbCafeL = radioGroup(carbCafeOpL, this, v -> carbCafeL = ((RadioButton)v).getText().toString());
        abaLeandro.addView(rgCarbCafeL);

        abaLeandro.addView(subTitulo("Proteína:", this));
        String[] protCafeOpL = {"4 ovos", "110g de frango desfiado", "48g de whey", "1 lata de atum"};
        RadioGroup rgProtCafeL = radioGroup(protCafeOpL, this, v -> protCafeL = ((RadioButton)v).getText().toString());
        abaLeandro.addView(rgProtCafeL);

        abaLeandro.addView(divisor(this));
        abaLeandro.addView(secao("🍽️ Almoço × 7 + Janta × 7", this));

        abaLeandro.addView(subTitulo("Carboidrato:", this));
        String[] carbAlmOpL = {"65g de arroz", "115g de arroz", "185g de purê de batata", "105g de mandioca", "130g de macarrão"};
        RadioGroup rgCarbAlmL = radioGroup(carbAlmOpL, this, v -> carbAlmL = ((RadioButton)v).getText().toString());
        abaLeandro.addView(rgCarbAlmL);

        abaLeandro.addView(subTitulo("Proteína:", this));
        String[] protAlmOpL = {"135g de sobrecoxa s/ pele", "100g de frango", "170g de tilápia", "105g de patinho", "150g de lombo"};
        RadioGroup rgProtAlmL = radioGroup(protAlmOpL, this, v -> protAlmL = ((RadioButton)v).getText().toString());
        abaLeandro.addView(rgProtAlmL);
        abaLeandro.addView(itemFixo("✔ 100g de legumes (fixo)", this));

        abaLeandro.addView(divisor(this));
        abaLeandro.addView(secao("🥤 Lanche da Tarde × 5", this));
        String[] lancheOpL = {
            "Shake: 150ml leite + 48g whey + 1 banana + 30g aveia",
            "110g Arroz + 100g Frango",
            "Panqueca: 1 banana + 150ml leite + 30g aveia + 48g whey + 1 ovo"
        };
        RadioGroup rgLancheL = radioGroup(lancheOpL, this, v -> lancheL = ((RadioButton)v).getText().toString());
        abaLeandro.addView(rgLancheL);

        scrollL.addView(abaLeandro);

        TabHost.TabSpec specL = tabHost.newTabSpec("Leandro");
        specL.setIndicator("Leandro");
        specL.setContent(tag -> scrollL);
        tabHost.addTab(specL);

        // ===== ABA DÉBORA =====
        ScrollView scrollD = new ScrollView(this);
        LinearLayout abaDebora = new LinearLayout(this);
        abaDebora.setOrientation(LinearLayout.VERTICAL);
        abaDebora.setPadding(24, 24, 24, 24);

        abaDebora.addView(secao("☀️ Café da Manhã × 5", this));
        abaDebora.addView(subTitulo("Carboidrato:", this));
        String[] carbCafeOpD = {
            "50g morango + 50g melão + 20g aveia + 50g mamão",
            "2 fatias de pão integral",
            "1 pão francês",
            "100g de cuzcuz"
        };
        RadioGroup rgCarbCafeD = radioGroup(carbCafeOpD, this, v -> carbCafeD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgCarbCafeD);

        abaDebora.addView(subTitulo("Proteína:", this));
        String[] protCafeOpD = {"2 ovos", "80g de frango desfiado", "40g de whey", "1 lata de atum"};
        RadioGroup rgProtCafeD = radioGroup(protCafeOpD, this, v -> protCafeD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgProtCafeD);

        abaDebora.addView(divisor(this));
        abaDebora.addView(secao("🍽️ Almoço × 7", this));
        abaDebora.addView(subTitulo("Carboidrato:", this));
        String[] carbAlmOpD = {"50g de arroz", "90g de arroz", "150g de purê de batata", "100g de batata doce", "200g de abóbora cabotiá"};
        RadioGroup rgCarbAlmD = radioGroup(carbAlmOpD, this, v -> carbAlmD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgCarbAlmD);

        abaDebora.addView(subTitulo("Proteína:", this));
        String[] protAlmOpD = {"80g de frango", "6 claras + 1 ovo", "100g de tilápia", "80g de patinho", "50g de proteína de soja", "75g de lombo"};
        RadioGroup rgProtAlmD = radioGroup(protAlmOpD, this, v -> protAlmD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgProtAlmD);
        abaDebora.addView(itemFixo("✔ 100g de legumes (fixo)", this));

        abaDebora.addView(divisor(this));
        abaDebora.addView(secao("🥤 Lanche da Tarde × 5", this));
        String[] lancheOpD = {
            "Pão integral + cottage + 80g frango",
            "Pão integral + cottage + atum",
            "0,5 maçã + iogurte + 20g whey",
            "1 banana + iogurte + 20g whey",
            "Banana + 120ml leite + 20g aveia + 40g whey"
        };
        RadioGroup rgLancheD = radioGroup(lancheOpD, this, v -> lancheD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgLancheD);

        abaDebora.addView(divisor(this));
        abaDebora.addView(secao("🌙 Jantar × 7", this));
        abaDebora.addView(subTitulo("Carboidrato:", this));
        String[] carbJantarOpD = {"90g de arroz", "150g de batata inglesa", "100g de batata doce", "200g de abóbora cabotiá"};
        RadioGroup rgCarbJantarD = radioGroup(carbJantarOpD, this, v -> carbJantarD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgCarbJantarD);

        abaDebora.addView(subTitulo("Proteína:", this));
        String[] protJantarOpD = {"80g de frango", "100g de tilápia", "80g de patinho", "75g de lombo"};
        RadioGroup rgProtJantarD = radioGroup(protJantarOpD, this, v -> protJantarD = ((RadioButton)v).getText().toString());
        abaDebora.addView(rgProtJantarD);
        abaDebora.addView(itemFixo("✔ 100g de legumes (fixo)", this));

        scrollD.addView(abaDebora);

        TabHost.TabSpec specD = tabHost.newTabSpec("Débora");
        specD.setIndicator("Débora");
        specD.setContent(tag -> scrollD);
        tabHost.addTab(specD);

        // ===== ABA LISTA DE COMPRAS =====
        ScrollView scrollC = new ScrollView(this);
        LinearLayout abaCompra = new LinearLayout(this);
        abaCompra.setOrientation(LinearLayout.VERTICAL);
        abaCompra.setPadding(24, 24, 24, 24);

        abaCompra.addView(secao("🛒 Lista de Compras Semanal", this));

        // Botão gerar lista
        Button btnGerar = new Button(this);
        btnGerar.setText("📋 Gerar Lista de Compras");
        btnGerar.setBackgroundColor(COR_PRIMARIA);
        btnGerar.setTextColor(COR_BRANCO);
        btnGerar.setTypeface(null, Typeface.BOLD);
        btnGerar.setPadding(32, 24, 32, 24);
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(0, 8, 0, 24);
        btnGerar.setLayoutParams(btnParams);

        LinearLayout containerLista = new LinearLayout(this);
        containerLista.setOrientation(LinearLayout.VERTICAL);

        btnGerar.setOnClickListener(v -> gerarLista(containerLista));

        abaCompra.addView(btnGerar);
        abaCompra.addView(containerLista);
        scrollC.addView(abaCompra);

        TabHost.TabSpec specC = tabHost.newTabSpec("Compras");
        specC.setIndicator("🛒 Compras");
        specC.setContent(tag -> scrollC);
        tabHost.addTab(specC);

        root.addView(tabHost);
        scroll.addView(root);
        setContentView(scroll);
    }

    // ===================== GERAR LISTA DE COMPRAS =====================
    void gerarLista(LinearLayout container) {
        container.removeAllViews();

        // Validação
        if (carbCafeL.isEmpty() || protCafeL.isEmpty() || carbAlmL.isEmpty() ||
            protAlmL.isEmpty() || lancheL.isEmpty() || carbCafeD.isEmpty() ||
            protCafeD.isEmpty() || carbAlmD.isEmpty() || protAlmD.isEmpty() ||
            lancheD.isEmpty() || carbJantarD.isEmpty() || protJantarD.isEmpty()) {

            TextView aviso = new TextView(this);
            aviso.setText("⚠️ Preencha todas as opções nas abas Leandro e Débora antes de gerar a lista.");
            aviso.setTextColor(Color.parseColor("#E65100"));
            aviso.setBackgroundColor(Color.parseColor("#FFF3E0"));
            aviso.setPadding(24, 20, 24, 20);
            aviso.setTextSize(14);
            container.addView(aviso);
            return;
        }

        Map<String, Double> compras = new LinkedHashMap<>();

        // ===== LEANDRO =====
        adicionar(compras, "fatia de mussarela", 5);

        // Café L
        if (carbCafeL.contains("pão integral"))        adicionar(compras, "fatias de pão integral", 15);
        else if (carbCafeL.contains("pão francês"))  { adicionar(compras, "pães franceses", 5); adicionar(compras, "peras", 5); }
        else if (carbCafeL.contains("tapioca"))        adicionar(compras, "g de tapioca", 275);
        else if (carbCafeL.contains("maçã"))         { adicionar(compras, "maçãs", 2.5); adicionar(compras, "bananas pequenas", 5); adicionar(compras, "copos de iogurte desnatado", 5); }

        if      (protCafeL.contains("ovos"))           adicionar(compras, "ovos", 20);
        else if (protCafeL.contains("frango"))         adicionar(compras, "g de frango", 550);
        else if (protCafeL.contains("whey"))           adicionar(compras, "g de whey", 240);
        else if (protCafeL.contains("atum"))           adicionar(compras, "latas de atum", 5);

        // Almoço + Janta L (14x)
        if (carbAlmL.contains("arroz"))              { int q = extrairGramas(carbAlmL); adicionar(compras, "g de arroz", q * 14); adicionar(compras, "g de feijão", 200*14); }
        else if (carbAlmL.contains("purê"))          { adicionar(compras, "g de purê de batata", extrairGramas(carbAlmL) * 14); }
        else if (carbAlmL.contains("mandioca"))        adicionar(compras, "g de mandioca", extrairGramas(carbAlmL) * 14);
        else if (carbAlmL.contains("macarrão"))        adicionar(compras, "g de macarrão", extrairGramas(carbAlmL) * 14);

        if      (protAlmL.contains("frango"))          adicionar(compras, "g de frango", 100*14);
        else if (protAlmL.contains("sobrecoxa"))       adicionar(compras, "g de sobrecoxa s/ pele", 135*14);
        else if (protAlmL.contains("tilápia"))         adicionar(compras, "g de tilápia", 170*14);
        else if (protAlmL.contains("patinho"))         adicionar(compras, "g de patinho", 105*14);
        else if (protAlmL.contains("lombo"))           adicionar(compras, "g de lombo", 150*14);

        adicionar(compras, "g de legumes", 100*14);

        // Lanche L
        if (lancheL.contains("Shake") || lancheL.contains("shake") || lancheL.contains("150ml")) {
            adicionar(compras, "ml de leite desnatado", 150*5);
            adicionar(compras, "g de whey", 48*5);
            adicionar(compras, "bananas", 5);
            adicionar(compras, "g de aveia", 30*5);
        } else if (lancheL.contains("Arroz") || lancheL.contains("110g")) {
            adicionar(compras, "g de arroz", 110*5);
            adicionar(compras, "g de frango", 100*5);
        } else if (lancheL.contains("Panqueca") || lancheL.contains("panqueca")) {
            adicionar(compras, "bananas", 5);
            adicionar(compras, "ml de leite desnatado", 150*5);
            adicionar(compras, "g de aveia", 30*5);
            adicionar(compras, "g de whey", 48*5);
            adicionar(compras, "ovos", 5);
        }

        // ===== DÉBORA =====
        // Café D
        if (carbCafeD.contains("morango"))           { adicionar(compras, "g de morango", 250); adicionar(compras, "g de melão", 250); adicionar(compras, "g de aveia", 100); adicionar(compras, "g de mamão", 250); }
        else if (carbCafeD.contains("pão integral"))   adicionar(compras, "fatias de pão integral", 10);
        else if (carbCafeD.contains("pão francês"))    adicionar(compras, "pães franceses", 5);
        else if (carbCafeD.contains("cuzcuz"))         adicionar(compras, "g de cuzcuz", 500);

        if      (protCafeD.contains("ovos"))           adicionar(compras, "ovos", 10);
        else if (protCafeD.contains("frango"))         adicionar(compras, "g de frango", 400);
        else if (protCafeD.contains("whey"))           adicionar(compras, "g de whey", 200);
        else if (protCafeD.contains("atum"))           adicionar(compras, "latas de atum", 5);

        // Almoço D (7x)
        if (carbAlmD.contains("arroz"))              { adicionar(compras, "g de arroz", extrairGramas(carbAlmD)*7); }
        else if (carbAlmD.contains("purê"))            adicionar(compras, "g de purê de batata", extrairGramas(carbAlmD)*7);
        else if (carbAlmD.contains("batata doce"))     adicionar(compras, "g de batata doce", extrairGramas(carbAlmD)*7);
        else if (carbAlmD.contains("abóbora"))         adicionar(compras, "g de abóbora cabotiá", extrairGramas(carbAlmD)*7);

        if      (protAlmD.contains("frango"))          adicionar(compras, "g de frango", 80*7);
        else if (protAlmD.contains("claras"))        { adicionar(compras, "ovos", 7*7); }
        else if (protAlmD.contains("tilápia"))         adicionar(compras, "g de tilápia", 100*7);
        else if (protAlmD.contains("patinho"))         adicionar(compras, "g de patinho", 80*7);
        else if (protAlmD.contains("soja"))            adicionar(compras, "g de proteína de soja", 50*7);
        else if (protAlmD.contains("lombo"))           adicionar(compras, "g de lombo", 75*7);

        adicionar(compras, "g de legumes", 100*7);

        // Lanche D
        if (lancheD.contains("cottage")) {
            adicionar(compras, "fatias de pão integral", 10);
            adicionar(compras, "potes de queijo cottage", 5);
            if (lancheD.contains("frango")) adicionar(compras, "g de frango", 80*5);
            else                            adicionar(compras, "latas de atum", 5);
        } else if (lancheD.contains("maçã")) {
            adicionar(compras, "maçãs", 2.5);
            adicionar(compras, "iogurtes desnatados", 5);
            adicionar(compras, "g de whey", 20*5);
        } else if (lancheD.contains("banana") && lancheD.contains("iogurte")) {
            adicionar(compras, "bananas", 5);
            adicionar(compras, "iogurtes desnatados", 5);
            adicionar(compras, "g de whey", 20*5);
        } else if (lancheD.contains("aveia")) {
            adicionar(compras, "bananas pequenas", 5);
            adicionar(compras, "ml de leite desnatado", 120*5);
            adicionar(compras, "g de aveia", 20*5);
            adicionar(compras, "g de whey", 40*5);
        }

        // Jantar D (7x)
        if (carbJantarD.contains("arroz"))             adicionar(compras, "g de arroz", extrairGramas(carbJantarD)*7);
        else if (carbJantarD.contains("inglesa"))       adicionar(compras, "g de batata inglesa", 150*7);
        else if (carbJantarD.contains("batata doce"))   adicionar(compras, "g de batata doce", 100*7);
        else if (carbJantarD.contains("abóbora"))       adicionar(compras, "g de abóbora cabotiá", 200*7);

        if      (protJantarD.contains("frango"))        adicionar(compras, "g de frango", 80*7);
        else if (protJantarD.contains("tilápia"))       adicionar(compras, "g de tilápia", 100*7);
        else if (protJantarD.contains("patinho"))       adicionar(compras, "g de patinho", 80*7);
        else if (protJantarD.contains("lombo"))         adicionar(compras, "g de lombo", 75*7);

        adicionar(compras, "g de legumes", 100*7);

        // ===== EXIBIR POR CATEGORIA =====
        Map<String, List<String>> cats = new LinkedHashMap<>();
        cats.put("🥬 Frutas",              new ArrayList<>());
        cats.put("🥚 Proteínas / Carnes",  new ArrayList<>());
        cats.put("🍞 Carboidratos",         new ArrayList<>());
        cats.put("🥛 Lácteos",             new ArrayList<>());
        cats.put("🌿 Legumes",             new ArrayList<>());
        cats.put("🥣 Outros",              new ArrayList<>());

        for (Map.Entry<String, Double> e : compras.entrySet()) {
            String item = e.getKey();
            double qtd  = e.getValue();
            String label = (qtd == Math.floor(qtd))
                ? String.format("%d %s", (int)qtd, item)
                : String.format("%.1f %s", qtd, item);

            String il = item.toLowerCase();
            if (il.matches(".*(morango|melão|mamão|maçã|banana|pera).*"))
                cats.get("🥬 Frutas").add(label);
            else if (il.matches(".*(frango|ovos|whey|atum|tilápia|patinho|lombo|sobrecoxa|cottage|soja).*"))
                cats.get("🥚 Proteínas / Carnes").add(label);
            else if (il.matches(".*(arroz|pão|macarrão|tapioca|cuzcuz|batata|abóbora|feijão|purê|aveia|mandioca).*"))
                cats.get("🍞 Carboidratos").add(label);
            else if (il.matches(".*(leite|iogurte|mussarela).*"))
                cats.get("🥛 Lácteos").add(label);
            else if (il.contains("legumes"))
                cats.get("🌿 Legumes").add(label);
            else
                cats.get("🥣 Outros").add(label);
        }

        for (Map.Entry<String, List<String>> cat : cats.entrySet()) {
            if (cat.getValue().isEmpty()) continue;

            // Cabeçalho categoria
            TextView catTv = new TextView(this);
            catTv.setText(cat.getKey());
            catTv.setTextSize(16);
            catTv.setTypeface(null, Typeface.BOLD);
            catTv.setTextColor(COR_TEXTO);
            catTv.setBackgroundColor(COR_DIVISOR);
            catTv.setPadding(16, 12, 16, 12);
            LinearLayout.LayoutParams catParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            catParams.setMargins(0, 16, 0, 4);
            catTv.setLayoutParams(catParams);
            container.addView(catTv);

            for (String itemLabel : cat.getValue()) {
                CheckBox cb = new CheckBox(this);
                cb.setText(itemLabel);
                cb.setTextSize(14);
                cb.setTextColor(Color.parseColor("#333333"));
                cb.setPadding(8, 10, 8, 10);
                container.addView(cb);
            }
        }
    }

    // ===================== UTILITÁRIOS =====================
    void adicionar(Map<String, Double> mapa, String item, double qtd) {
        mapa.put(item, mapa.getOrDefault(item, 0.0) + qtd);
    }

    int extrairGramas(String texto) {
        try {
            String num = texto.replaceAll("[^0-9]", "").trim();
            if (num.isEmpty()) return 0;
            return Integer.parseInt(num.substring(0, Math.min(num.length(), 4)));
        } catch (Exception e) { return 0; }
    }

    // ===== Componentes de UI =====
    TextView secao(String texto, Context ctx) {
        TextView tv = new TextView(ctx);
        tv.setText(texto);
        tv.setTextSize(17);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextColor(COR_TEXTO);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 16, 0, 8);
        tv.setLayoutParams(p);
        return tv;
    }

    TextView subTitulo(String texto, Context ctx) {
        TextView tv = new TextView(ctx);
        tv.setText(texto);
        tv.setTextSize(14);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextColor(Color.parseColor("#558B2F"));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 12, 0, 4);
        tv.setLayoutParams(p);
        return tv;
    }

    TextView itemFixo(String texto, Context ctx) {
        TextView tv = new TextView(ctx);
        tv.setText(texto);
        tv.setTextSize(14);
        tv.setTextColor(Color.parseColor("#2E7D32"));
        tv.setBackgroundColor(Color.parseColor("#E8F5E9"));
        tv.setPadding(16, 10, 16, 10);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 8, 0, 8);
        tv.setLayoutParams(p);
        return tv;
    }

    View divisor(Context ctx) {
        View v = new View(ctx);
        v.setBackgroundColor(COR_DIVISOR);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 2);
        p.setMargins(0, 20, 0, 8);
        v.setLayoutParams(p);
        return v;
    }

    RadioGroup radioGroup(String[] opcoes, Context ctx, View.OnClickListener listener) {
        RadioGroup rg = new RadioGroup(ctx);
        rg.setOrientation(RadioGroup.VERTICAL);
        for (String op : opcoes) {
            RadioButton rb = new RadioButton(ctx);
            rb.setText(op);
            rb.setTextSize(13);
            rb.setTextColor(Color.parseColor("#333333"));
            rb.setPadding(8, 8, 8, 8);
            rb.setOnClickListener(listener);
            rg.addView(rb);
        }
        return rg;
    }
}
