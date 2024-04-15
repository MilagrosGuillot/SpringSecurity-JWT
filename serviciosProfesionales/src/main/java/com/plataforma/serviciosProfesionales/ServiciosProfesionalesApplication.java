package com.plataforma.serviciosProfesionales;


import com.plataforma.serviciosProfesionales.persistence.repository.UsuarioRepository;
import com.plataforma.serviciosProfesionales.persistence.entitys.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ServiciosProfesionalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosProfesionalesApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository userRepository) {
		return args -> {
			/* Create PERMISSIONS */
			Permisos createPermission = Permisos.builder()
					.name("CREATE")
					.build();

			Permisos readPermission = Permisos.builder()
					.name("READ")
					.build();

			Permisos updatePermission = Permisos.builder()
					.name("UPDATE")
					.build();

			Permisos deletePermission = Permisos.builder()
					.name("DELETE")
					.build();

			Permisos refactorPermission = Permisos.builder()
					.name("REFACTOR")
					.build();

			/* Create ROLES */
			Rol roleAdmin = Rol.builder()
					.rolEnum(RolEnum.ADMINISTRADOR)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Rol roleUser = Rol.builder()
					.rolEnum(RolEnum.USUARIO_NORMAL)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			Rol roleInvited = Rol.builder()
					.rolEnum(RolEnum.ADMINISTRADOR)
					.permissionList(Set.of(readPermission))
					.build();

			Rol roleDeveloper = Rol.builder()
					.rolEnum(RolEnum.SOPORTE)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			/* CREATE categoriasDeTrabajos */
			CategoriasDeTrabajo categoriasInformatica = CategoriasDeTrabajo.builder()
					.Descripcion("sabe mucho")
					.categorias(Categorias.INFORMATICA)
					.build();

			CategoriasDeTrabajo categoriaspeluquero = CategoriasDeTrabajo.builder()
					.Descripcion("corta pelo")
					.categorias(Categorias.PELUQUERO)
					.build();

			CategoriasDeTrabajo categoriasProfesor = CategoriasDeTrabajo.builder()
					.Descripcion("profesor")
					.categorias(Categorias.PROFESOR)
					.build();

			Calificaciones calificaciones = Calificaciones.builder()
					.opinion("bueno")
					.calificacion("4")
					.build();

			Calificaciones calificaciones2 = Calificaciones.builder()
					.opinion("malo")
					.calificacion("4")
					.build();

			Calificaciones calificaciones3 = Calificaciones.builder()
					.opinion("hmm")
					.calificacion("4")
					.build();

			/* CREATE Profesional */
			Profesional profesionalinformatico = Profesional.builder()
					.username("Carlos")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.Correo("carlosi@gmail.com")
					.DNI("43450253")
					.categoriasDeTrabajos(Set.of(categoriasInformatica))
					.calificaciones(calificaciones)
					.build();

			Profesional profesionalPeluquero = Profesional.builder()
					.username("Gamaliel")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.Correo("Gamalieli@gmail.com")
					.DNI("43450253")
					.categoriasDeTrabajos(Set.of(categoriaspeluquero))
					.calificaciones(calificaciones2)
					.build();

			Profesional profesionalProfesor = Profesional.builder()
					.username("Carlos")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.Correo("carlosi@gmail.com")
					.DNI("43450253")
					.categoriasDeTrabajos(Set.of(categoriasProfesor))
					.calificaciones(calificaciones3)
					.build();

			/* CREATE USERS */
			Usuario userSantiago = Usuario.builder()
					.username("santiago")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.isEnabled(true) //la cuenta esta activa
					.accountNoExpired(true) //la cuenta no ha expirado
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.profesional(Set.of(profesionalinformatico))
					.build();

			Usuario userDaniel = Usuario.builder()
					.username("daniel")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.profesional(Set.of(profesionalPeluquero))
					.build();

			Usuario userAndrea = Usuario.builder()
					.username("andrea")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.profesional(Set.of(profesionalProfesor))
					.build();

			Usuario userAnyi = Usuario.builder()
					.username("anyi")
					.password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));
		};
	}
}
