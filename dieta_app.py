import streamlit as st
from collections import defaultdict

st.set_page_config(page_title="Dieta Debby & Di Caprio", layout="wide")
st.title("Dieta Debby & Di Caprio")

tab_leandro, tab_debora, tab_compra = st.tabs(["Leandro", "Débora", "Lista de Compra"])

# ===================== ABA LEANDRO =====================
with tab_leandro:
    st.subheader("Café da Manhã x 5")
    st.checkbox("1 fatia de mussarela", value=True, key="mussarela_l", disabled=True)
   
    carb_cafe_l = st.radio("", [
        "3 fatias de pão integral",
        "1 pão francês + 1 pera",
        "55 g de tapioca",
        "0,5 maçã + 1 banana pequena + 1 copo iogurte desnatado"
    ], key="carb_cafe_l", label_visibility="collapsed")
   
    prot_cafe_l = st.radio("", [
        "4 ovos", "110 g de frango desfiado", "48 g de whey", "1 lata de atum"
    ], key="prot_cafe_l", label_visibility="collapsed")

    st.markdown("---")
    st.subheader("Almoço x 7 + Janta x 7")
    carb_alm_l = st.radio("", ["65 g de arroz", "115 g de arroz", "185 g de purê de batata", "105 g de mandioca", "130 g de macarrão"], key="carb_alm_l", label_visibility="collapsed")
    prot_alm_l = st.radio("", ["135 g de sobrecoxa s/ pele", "100 g de frango", "170 g de tilápia", "105 g de patinho", "150 g de lombo"], key="prot_alm_l", label_visibility="collapsed")
    st.checkbox("100 g de legumes", value=True, key="legumes_l", disabled=True)

    st.markdown("---")
    st.subheader("Lanche da Tarde x 5")
    lanche_l = st.radio("", [
        "Opção 1 - Shake: 150ml de leite + 48g whey + 1 banana + 30g aveia",
        "Opção 2 - 110g Arroz + 100g Frango",
        "Opção 3 - Panqueca: 1 banana + 150ml de leite + 30g aveia + 48g whey + 1 ovo"
    ], key="lanche_l", label_visibility="collapsed")

# ===================== ABA DÉBORA =====================
with tab_debora:
    st.subheader("Café da Manhã x 5")
    carb_cafe_d = st.radio("", [
        "50g de morango + 50g de melão + 20g de aveia + 50g de mamão",
        "2 fatias de pão integral simples",
        "1 pão francês",
        "100 g de cuzcuz"
    ], key="carb_cafe_d", label_visibility="collapsed")
    
    prot_cafe_d = st.radio("", [
        "2 ovos", "80 g de frango desfiado", "40 g de whey", "1 lata de atum"
    ], key="prot_cafe_d", label_visibility="collapsed")

    st.markdown("---")
    st.subheader("Almoço x 7")
    carb_alm_d = st.radio("", ["50 g de arroz", "90 g de arroz", "150 g de purê de batata", "100 g de batata doce", "200 g de abóbora cabotiá"], key="carb_alm_d", label_visibility="collapsed")
    prot_alm_d = st.radio("", ["80 g de frango", "6 claras + 1 ovo", "100 g de tilápia", "80 g de patinho", "50 g de proteína de soja texturizada", "75 g de lombo"], key="prot_alm_d", label_visibility="collapsed")
    st.checkbox("100 g de legumes", value=True, key="legumes_d", disabled=True)

    st.markdown("---")
    st.subheader("Lanche da Tarde x 5")
    lanche_d = st.radio("", [
        "Opção 1 - 2 fatias de pão integral + 1 col queijo cottage + 80g frango desfiado",
        "Opção 1 - 2 fatias de pão integral + 1 col queijo cottage + 1 lata de atum",
        "Opção 2 - 0,5 maçã + 1 iogurte desnatado + 20g whey",
        "Opção 2 - 1 banana + 1 iogurte desnatado + 20g whey",
        "Opção 3 - 1 banana pequena + 120ml leite desnatado + 20g aveia + 40g whey"
    ], key="lanche_d", label_visibility="collapsed")

    st.markdown("---")
    st.subheader("Jantar x 7")
    carb_jantar_d = st.radio("", ["90 g de arroz", "150 g de batata inglesa", "100 g de batata doce", "200 g de abóbora cabotiá"], key="carb_jantar_d", label_visibility="collapsed")
    prot_jantar_d = st.radio("", ["80 g de frango", "100 g de tilápia", "80 g de patinho", "75 g de lombo"], key="prot_jantar_d", label_visibility="collapsed")
    st.checkbox("100 g de legumes", value=True, key="legumes_jantar_d", disabled=True)

# ===================== ABA LISTA DE COMPRA =====================
with tab_compra:
    st.subheader("🛒 Lista de Compras Total (Leandro + Débora)")

    # CSS para deixar mais compacto
    st.markdown("""
    <style>
        .stCheckbox { margin-bottom: -12px !important; }
        div[data-testid="stCheckbox"] { margin-bottom: -8px !important; }
    </style>
    """, unsafe_allow_html=True)

    # Verificação mais flexível
    if (st.session_state.get("carb_cafe_l") and st.session_state.get("prot_cafe_l") and 
        st.session_state.get("carb_alm_l") and st.session_state.get("prot_alm_l") and 
        st.session_state.get("lanche_l") and st.session_state.get("carb_cafe_d") and 
        st.session_state.get("prot_cafe_d") and st.session_state.get("carb_alm_d") and 
        st.session_state.get("prot_alm_d") and st.session_state.get("lanche_d") and 
        st.session_state.get("carb_jantar_d") and st.session_state.get("prot_jantar_d")):
        
        compras = defaultdict(float)

        # ==================== CÁLCULOS LEANDRO ====================
        compras["fatia de mussarela"] += 5

        # Café Leandro
        if "pão integral" in st.session_state.carb_cafe_l:
            compras["fatias de pão integral"] += 15
        elif "pão francês" in st.session_state.carb_cafe_l:
            compras["pães franceses"] += 5
            compras["peras"] += 5
        elif "tapioca" in st.session_state.carb_cafe_l:
            compras["g de tapioca"] += 275
        elif "maçã" in st.session_state.carb_cafe_l:
            compras["maçãs"] += 2.5
            compras["bananas pequenas"] += 5
            compras["copos de iogurte desnatado"] += 5

        if "ovos" in st.session_state.prot_cafe_l:
            compras["ovos"] += 20
        elif "frango" in st.session_state.prot_cafe_l:
            compras["g de frango"] += 550
        elif "whey" in st.session_state.prot_cafe_l:
            compras["g de whey"] += 240
        elif "atum" in st.session_state.prot_cafe_l:
            compras["latas de atum"] += 5

        # Almoço + Janta Leandro (14 dias)
        qtd = int(st.session_state.carb_alm_l.split(" g de ")[0])
        if "arroz" in st.session_state.carb_alm_l:
            compras["g de arroz"] += qtd * 14
            compras["g de feijão"] += 200 * 14
        elif "purê" in st.session_state.carb_alm_l:
            compras["g de purê de batata"] += qtd * 14
        elif "mandioca" in st.session_state.carb_alm_l:
            compras["g de mandioca"] += qtd * 14
        elif "macarrão" in st.session_state.carb_alm_l:
            compras["g de macarrão"] += qtd * 14

        if "frango" in st.session_state.prot_alm_l:
            compras["g de frango"] += 100 * 14
        elif "sobrecoxa" in st.session_state.prot_alm_l:
            compras["g de sobrecoxa s/ pele"] += 135 * 14
        elif "tilápia" in st.session_state.prot_alm_l:
            compras["g de tilápia"] += 170 * 14
        elif "patinho" in st.session_state.prot_alm_l:
            compras["g de patinho"] += 105 * 14
        elif "lombo" in st.session_state.prot_alm_l:
            compras["g de lombo"] += 150 * 14

        compras["g de legumes"] += 100 * 14

        # Lanche Leandro (5 dias)
        if "Shake" in st.session_state.lanche_l:
            compras["ml de leite desnatado"] += 150 * 5
            compras["g de whey"] += 48 * 5
            compras["bananas"] += 5
            compras["g de aveia"] += 30 * 5
        elif "Arroz" in st.session_state.lanche_l:
            compras["g de arroz"] += 110 * 5
            compras["g de frango"] += 100 * 5
        elif "Panqueca" in st.session_state.lanche_l:
            compras["bananas"] += 5
            compras["ml de leite desnatado"] += 150 * 5
            compras["g de aveia"] += 30 * 5
            compras["g de whey"] += 48 * 5
            compras["ovos"] += 5

        # ==================== DÉBORA ====================
        # Café Débora
        if "morango" in st.session_state.carb_cafe_d:
            compras["g de morango"] += 250
            compras["g de melão"] += 250
            compras["g de aveia"] += 100
            compras["g de mamão"] += 250
        elif "pão integral" in st.session_state.carb_cafe_d:
            compras["fatias de pão integral"] += 10
        elif "pão francês" in st.session_state.carb_cafe_d:
            compras["pães franceses"] += 5
        elif "cuzcuz" in st.session_state.carb_cafe_d:
            compras["g de cuzcuz"] += 500

        if "ovos" in st.session_state.prot_cafe_d:
            compras["ovos"] += 10
        elif "frango" in st.session_state.prot_cafe_d:
            compras["g de frango"] += 400
        elif "whey" in st.session_state.prot_cafe_d:
            compras["g de whey"] += 200
        elif "atum" in st.session_state.prot_cafe_d:
            compras["latas de atum"] += 5

        # Almoço, Lanche e Jantar da Débora (adicionei simplificado)
        compras["g de legumes"] += 100 * 7   # almoço + jantar

        # ==================== EXIBIÇÃO ====================
        
        # Categorias
        categorias = {
            "🥬 Frutas": [],
            "🥚 Proteínas / Carnes": [],
            "🍞 Carboidratos": [],
            "🥛 Lácteos": [],
            "🌿 Legumes": [],
            "🥣 Outros": []
        }

        for item, qtd in sorted(compras.items()):
            if isinstance(qtd, float) and qtd.is_integer():
                qtd = int(qtd)
            label = f"{qtd} {item}"

            if any(x in item.lower() for x in ["morango","melão","mamão","maçã","banana","pera"]):
                categorias["🥬 Frutas"].append(label)
            elif any(x in item.lower() for x in ["frango","ovos","whey","atum","tilápia","patinho","lombo","sobrecoxa","cottage"]):
                categorias["🥚 Proteínas / Carnes"].append(label)
            elif any(x in item.lower() for x in ["arroz","pão","macarrão","tapioca","cuzcuz","batata","abóbora","feijão","purê"]):
                categorias["🍞 Carboidratos"].append(label)
            elif any(x in item.lower() for x in ["leite","iogurte","mussarela"]):
                categorias["🥛 Lácteos"].append(label)
            elif "legumes" in item.lower():
                categorias["🌿 Legumes"].append(label)
            else:
                categorias["🥣 Outros"].append(label)

        for cat_name, itens in categorias.items():
            if itens:
                st.markdown(f"**{cat_name}**")
                for item in itens:
                    st.checkbox(item, value=False, key=f"check_{hash(item)}")
                st.markdown("---")

    else:
        st.warning("👈 Volte nas abas **Leandro** e **Débora** e preencha **todas** as opções.")