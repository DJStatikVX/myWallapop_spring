package com.uniovi.services;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.ConversationRepository;
import com.uniovi.repositories.OffersRepository;

@Service	//se puede desactivar el servicio quitando esta etiqueta
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private OffersService offersService;

	@Autowired 
	private OffersRepository offerRepository;
	
	@Autowired 
	private ConversationRepository converRepository;
	
	@SuppressWarnings("serial")
	@PostConstruct
	public void init() {
		User user1 = new User("taso@email", "Taso", "Rodriguez");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("Samu@correo", "Samuel", "Rodriguez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		user2.setMoney(1000);
		User user3 = new User("paco@correo", "Paco", "Paquez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("correoDeMarta", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		user4.setMoney(3017);
		User user5 = new User("admin@email.com", "admin", "");
		user5.setPassword("admin");
		user5.setRole(rolesService.getRoles()[1]);
		User user6 = new User("ibaiCorreo", "Ibai", "Youtuber");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[0]);
		user6.setMoney(1453);
		User user7 = new User("alexelcapo", "Alejandro", "El Capo");
		user7.setPassword("123456");
		user7.setRole(rolesService.getRoles()[0]);
		User user8 = new User("narutoFan1", "Osama", "Bin Laden");
		user8.setPassword("123456");
		user8.setRole(rolesService.getRoles()[0]);
		
		User user9 = new User("pruebaSaldo0", "prueba", "pruebez");
		user9.setPassword("123456");
		user9.setRole(rolesService.getRoles()[0]);
		user9.setMoney(25);
		

		
		Offer offer31 = new Offer("Tremendo Knardo" ,
				"Paquete de CBD medicinal 50 ml homologado",4.20, user3);
		
		Offer offer32 = new Offer("Balón Futbol",
				"Balon mundial 06 firmado por seleccion Italiana",221, user3);
		Offer offer33 = new Offer("PC Gaming",
				"PC gtx2060 i7 32 GB",560, user3);
		Set<Offer> user3Offers = new HashSet<Offer>() {
			{
				add(offer31);
				add(offer32);
				add(offer33);
			}
		};		
		user3.setOffers(user3Offers);
		
		
		
		Offer offer41 =new Offer("Silla Antigua" ,
				"Silla de ebano del S.XIX",32, user4);
		offer41.setPromoted(true);
		Offer offer42 =new Offer("Sello 1º Republica",
				"Sello de correos de la 1º Republica Española 1873",10, user4);
		Offer offer43 =new Offer("Ceramica china antignua",
				"Cazo de porcelana china del siglo 13",25, user4);
		Set<Offer> user4Offers = new HashSet<Offer>() {
			{
				add(offer41);
				add(offer42);
				add(offer43);
			}
		};
		user4.setOffers(user4Offers);
		
		Offer offer51 =new Offer("Bolsa de patatas" ,
				"Lays campesinas ",1.20, user5);
		Offer offer52 =new Offer("Camiseta del FCB",
				"Camiseta del FCBarcelona de la temporada 09",100, user5);
		Offer offer53 =new Offer("Mando de N64",
				"Mando original de la nintendo 64 en perfecto estado",60, user5);
		Set<Offer> user5Offers = new HashSet<Offer>() {
			{
				add(offer51);
				add(offer52);
				add(offer53);
			}
		};
		user5.setOffers(user5Offers);
		
		
		Offer offer61 =new Offer("Libro de Origami" ,
				"Origami Desing Secrets by Robert J. Lang",85.0, user6);
		Offer offer62 =new Offer("Abaco de cuentas",
				"Para contar cosas a lo antiguo",5, user6);
		Offer offer63 =new Offer("Tabla de logaritmos",
				"Tabla de logaritmos para hacer operaciones complejas"
				,8, user6);
		Set<Offer> user6Offers = new HashSet<Offer>() {
			{
				add(offer61);
				add(offer62);
				add(offer63);
			}
		};
		user6.setOffers(user6Offers);
		
		
		Offer offer71 =new Offer("Abrigo de esqki" ,
				"Abrigo para hacer esqui XL",30.0, user7);
		Offer offer72 =new Offer("Stick Pantera",
				"FightStick Pantera botones sanwa",110, user7);
		offer72.setPromoted(true);
		Offer offer73 =new Offer("Flexo de mesita",
				"pa estudiar",15, user7);
		Set<Offer> user7Offers = new HashSet<Offer>() {
			{
				add(offer71);
				add(offer72);
				add(offer73);
			}
		};
		user7.setOffers(user7Offers);
		
		
		
		Offer offer81 =new Offer("Tarta de cumpleaños" ,
				"Tarta de camilo de blas, mensaje: Felicidades",25.0, user8);
		Offer offer82 =new Offer("Cuchillo jamonero profesional",
				"Fabricado en Albacete de forma artesanal",110, user8);
		offer82.setPromoted(true);
		Offer offer83 =new Offer("Balon de balonmano",
				"Reglamentario de categoria cadete/senior femenino",15, user8);
		Set<Offer> user8Offers = new HashSet<Offer>() {
			{
				add(offer81);
				add(offer82);
				add(offer83);
			}
		};
		user8.setOffers(user8Offers);
		
		
		
		Offer offer11 = new Offer("Oferta 1: piscina", 
				"Piscina hinchable 5 metros cúbicos", 47.5, user1);
		Offer offer12 = new Offer("Oferta 2: ps4", 
				"PlayStation 4 con cinco meses de uso", 100.0, user1);
		Offer offer13 = new Offer("Botella de agua", 
				"Si estas en el desierto vale mucho", 1776.0, user1);
		
		Set<Offer> user1Offers = new HashSet<Offer>() {
			{
				add(offer11);
				add(offer12);
				add(offer13);
			}
		};		
		user1.setOffers(user1Offers);
		
		Offer offer21 = new Offer("Oferta 3: bate", 
				"Bate de béisbol nuevo del paquete", 10, user2);
		Offer offer22 = new Offer("Oferta 4: gogeta ssj4", 
				"Figura de Gogeta Super Saiyan 4", 25, user2);

		Offer offer23 = new Offer("Oferta 5: Altavoces Logitech", 
				"Modelo Z120. 2 meses de uso.", 19.99, user2);
		
		Set<Offer> user2Offers = new HashSet<Offer>() {
			{
				add(offer21);
				add(offer22);
				add(offer23);
			}
		};
		
		user2.setOffers(user2Offers);

		

		

		//añadimos una conversacion entre samu y taso por el producto offer22
		Conversation conver1 = new Conversation(offer22,user1);
		conver1.addMessage("Creo que esa figura que tienes es robada",
				user1.getEmail());
		conver1.addMessage("Si no me equivoco es la que gane yo en un torneo",
				user1.getEmail());
		conver1.addMessage("Se me perdio el otro dia",
				user1.getEmail());
		conver1.addMessage("Yo la encontre en una mesa de la universidad",
				user2.getEmail());
		conver1.addMessage("pero no te la voy a dar gratis",
				user2.getEmail());
		conver1.addMessage("si quieres podemos quedar un dia y si me "
				+ "demuestras que es tuya te hago un descuento",
				user2.getEmail());
		conver1.addMessage("Eres una rata, no sabes con quien te estas metiendo,"
				+ " voy a traer a los brothers y te vamos a partir las piernas,"
				+ " andate con ojo",
				user1.getEmail());	

		Conversation conver2 = new Conversation(offer32, user2);
		conver2.addMessage("¡Hola! Me gustaría adquirir este producto.", 
				user2.getEmail());
		conver2.addMessage("¿Se admite contrareembolso?", user2.getEmail());
		conver2.addMessage("Hola, Samuel. Un placer.", 
				offer32.getOwner().getEmail());
		conver2.addMessage("Sí, puedes pagarlo en mano.", 
				offer32.getOwner().getEmail());

		Conversation conver3 = new Conversation(offer41, user2);
		conver3.addMessage("¡Hola! ¡Vaya reliquia vendes!", 
				user2.getEmail());
		conver3.addMessage("¿En qué año fue confeccionada?", user2.getEmail());
		conver3.addMessage("Buenos días. Sí, me la quitan de las manos.", 
				offer41.getOwner().getEmail());
		conver3.addMessage("Concretamente este modelo corresponde al año 1837.", 
				offer41.getOwner().getEmail());
		
		Conversation conver4 = new Conversation(offer53, user2);
		conver4.addMessage("¡Hola! Un Nintendero por aquí.", 
				user2.getEmail());
		conver4.addMessage("¿De qué color es el mando?", user2.getEmail());
		conver4.addMessage("Buenas, ya somos dos, jeje.", 
				offer53.getOwner().getEmail());
		conver4.addMessage("Tengo los dos colores, dime cuál quieres " 
				+ "y te lo envío.", offer53.getOwner().getEmail());
		
		Conversation conver5 = new Conversation(offer72, user2);
		conver5.addMessage("¡Hola! Me interesa este producto...", 
				user2.getEmail());
		conver5.addMessage("Pero soy un poco grande. ¿Qué talla es?", 
				user2.getEmail());
		conver5.addMessage("Buenas. Tengo la 42.", 
				offer72.getOwner().getEmail());
		conver5.addMessage("Aunque podría conseguirte otras tallas superiores.", 
				offer72.getOwner().getEmail());
		
		Conversation conver6 = new Conversation(offer82, user1);
		conver6.addMessage("¡Hola! Estaba buscando un cuchillo jamonero...", 
				user1.getEmail());
		conver6.addMessage("Sé que es de Albacete y todo eso, pero me parece "
				+ "un poco caro.", user1.getEmail());
		conver6.addMessage("Hola. Este cuchillo lo fabricó mi abuelo y es "
				+ "muy valioso para mí.", 
				offer82.getOwner().getEmail());
		conver6.addMessage("Podemos negociarlo un poco. ¿Qué te parece?", 
				offer82.getOwner().getEmail());
		
		Conversation conver7 = new Conversation(offer12, user2);
		conver7.addMessage("Hola. Me interesaría adquirir esta consola.", 
				user2.getEmail());
		conver7.addMessage("¿Viene con algún juego?", user2.getEmail());
		conver7.addMessage("¡Buenas! Tengo el Crash Bandicoot N. Sane Trilogy. "
				+ "Puedo enviártelo con la consola si quieres.", 
				offer12.getOwner().getEmail());
		conver7.addMessage("O bien te puedo adjuntar una suscripción del 6 "
				+ "meses del online, que yo no le he dado uso.", 
				offer12.getOwner().getEmail());
		
		Conversation conver8 = new Conversation(offer23, user1);
		conver8.addMessage("Hola. Estoy buscando unos altavoces para el PC", 
				user1.getEmail());
		conver8.addMessage("¿Qué tal se escuchan estos?", user1.getEmail());
		conver8.addMessage("Hey, la verdad es que para lo que cuestan "
				+ "son una pasada.", offer23.getOwner().getEmail());
		conver8.addMessage("Yo en ellos he puesto reggaetón, dubstep, "
				+ "películas... Y nunca me han fallado, "
				+ "creo que no encontrarás nada mejor.", 
				offer23.getOwner().getEmail());
		
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		usersService.addUser(user7);
		usersService.addUser(user8);
		usersService.addUser(user9);
		usersService.addUser(user1);
		usersService.addUser(user2);
		
		offerRepository.save(offer11);
		offerRepository.save(offer21);
		offerRepository.save(offer13);
		offerRepository.save(offer42);
		offerRepository.save(offer73);
		offerRepository.save(offer83);
		offerRepository.save(offer72);
		offerRepository.save(offer52);
		offerRepository.save(offer62);
		offerRepository.save(offer12);
		offerRepository.save(offer61);
		offerRepository.save(offer81);
		offerRepository.save(offer31);
		offerRepository.save(offer51);
		offerRepository.save(offer33);
		offerRepository.save(offer71);

		converRepository.save(conver1);
		converRepository.save(conver2);
		converRepository.save(conver3);
		converRepository.save(conver4);
		converRepository.save(conver5);
		converRepository.save(conver6);
		converRepository.save(conver7);
		converRepository.save(conver8);

		offersService.buy(user3, offer11);
		offersService.buy(user3, offer21);
		offersService.buy(user4, offer13);
		offersService.buy(user4, offer42);
		offersService.buy(user5, offer73);
		offersService.buy(user5, offer83);
		offersService.buy(user6, offer72);
		offersService.buy(user6, offer52);
		offersService.buy(user7, offer62);
		offersService.buy(user7, offer12);
		offersService.buy(user8, offer61);
		offersService.buy(user8, offer81);

		offersService.buy(user1, offer31);
		offersService.buy(user1, offer51);
		offersService.buy(user2, offer33);
		offersService.buy(user2, offer71);	
		
		
		//para caso de prueba 38
		User user10 = new User("prueba38", "prueba38", "pruebez38");
		user10.setPassword("123456");
		user10.setRole(rolesService.getRoles()[0]);
		user10.setMoney(15);
		
		Offer offer101 = new Offer("oferta prueba38" ,
				"test",4.20, user10);
		Set<Offer> user10Offers = new HashSet<Offer>() {
			{
				add(offer101);
			}
		};		
		user10.setOffers(user10Offers);
		
		usersService.addUser(user10);
		
	}
	
}
