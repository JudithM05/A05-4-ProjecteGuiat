#Programa que defineix els camps d'un alumne
def usuari_schema(user):
    return {"idUsuari": user[0],
            "nom": user[1],
            "rol": user[2],
            "correu": user[3],
            "contrasenya": user[4],
            "localitat": user[5],
            "createdAt": user[6],
            "updatedAt": user[7]
            }

def usuaris_schema(users) -> list:
    return [usuari_schema(usuari) for usuari in usuaris]