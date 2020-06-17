<?php

namespace TransportBundle\Controller;

use FirstBundle\Entity\Child;
use AppBundle\Entity\User;
use http\Env\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\DependencyInjection\Tests\Compiler\J;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use TransportBundle\Entity\Driver;
use TransportBundle\Entity\Traffectation;
use TransportBundle\Entity\Vehicule;

class MobilepiController extends Controller
{
    public function loginAction(Request $request)
    {
        $users = $this->getDoctrine()->getManager()
            ->getRepository(User::class)->findAll();
        foreach ($users as $user) {
            if ($user->getUsername() == $request->get('username')) {
                if ($user->getPassword() == $request->get('password')) {
                    /** @var $session Session */
                    $session = $request->getSession();
                    $session->set('username',$request->get('username'));
                    $session->set('password',$request->get('password'));
                    return new JsonResponse(1);
                }
            }
        }
        return new JsonResponse(0);

    }

    public function createAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = new User();
        /** @var $session Session */
        $session = $request->getSession();
        $session->set('username',$request->get('username'));
        $session->set('password',$request->get('password'));
        $session->set('email',$request->get('email'));
        $user->setUsername($request->get('username'));
        $user->setPassword($request->get('password'));
        $user->setEmail($request->get('email'));
        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse($formatted);
    }

    public function getallaffAction()
    {
        $traffectaions = new Traffectation();
        $traffectaions = $this->getDoctrine()->getManager()->getRepository(Traffectation::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($traffectaions);
        return new JsonResponse($formatted);

    }

    public function sendemailAction()
    {
        $transport = (new \Swift_SmtpTransport('smtp.googlemail.com', 465, 'ssl'))
            ->setUsername('aziz13mth@gmail.com')
            ->setPassword('abdou15121963bab');

// Create the Mailer using your created Transport
        $mailer = new \Swift_Mailer($transport);
        $em = $this->getDoctrine()->getManager();
        $affectations = $em->getRepository(Driver::class)->findAll();


            $body = '<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

    <h2>Transport List</h2>

<table>

    <tr>
        <th>first name</th>
        <th>last name</th>
        <th>age</th>
        <th>adress</th>
        <th>phone number</th>
    </tr>';
        foreach ($affectations as $affect) {
    $body=$body.'
    <tr>
        <td>'.$affect->getFirstname().'</td>
        <td>'.$affect->getLastname().'</td>
        <td>'.$affect->getAge().'</td>
        <td>'.$affect->getAdress().'</td>
        <td>'.$affect->getPhonenumber().'</td>
    </tr>'
    ;}

    $body=$body.'
</table>
</body>
</html>';


            $message = (new \Swift_Message(('Response To Your Request')))
                ->setFrom(['aziz13mth@gmail.com' => 'KindoGarten'])
                ->setTo('mohamedaziz.mathlouthi@esprit.tn')
                ->setBody($body)
                ->setContentType('text/html');

// Send the message
            $mailer->send($message);


        return new JsonResponse(1);

    }
    public function createvehAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = new Vehicule();
        $user->setBrand($request->get('brand'));
        $user->setCapacity($request->get('capacity'));
        $user->setStatus($request->get('status'));
        $user->setType($request->get('type'));
        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse($formatted);
    }
    public function createdriverAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user = new Driver();
        $user->setFirstname($request->get('firstname'));
        $user->setLastname($request->get('lastname'));
        $user->setAge($request->get('age'));
        $user->setPhonenumber($request->get('phone'));
        $user->setAdress($request->get('adress'));
        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse($formatted);
    }
    public function vehiculesshowAction()
    {
        $traffectaions = new Vehicule();
        $traffectaions = $this->getDoctrine()->getManager()->getRepository(Vehicule::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($traffectaions);
        return new JsonResponse($formatted);
    }
    public function driversshowAction()
    {
        $traffectaions = new Driver();
        $traffectaions = $this->getDoctrine()->getManager()->getRepository(Driver::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($traffectaions);
        return new JsonResponse($formatted);
    }
    public function disconnectAction(Request $request)
    {
        //$this->get('security.token_storage')->setToken(null);
        //$this->get('Session')->
        $this->get('session')->clear();
        return new JsonResponse($request->getSession()->get('username'));
    }
    public function chooseparentAction(Request $req)
    {
        //$em=$this->getDoctrine()->getManager();
        //$user=$this->get('session')->get('id');
        //$userManager = $container->get('fos_user.user_manager');
        //$user->addRole('ROLE_PARENT');
        //$em->flush();
        return new JsonResponse($req->getSession()->get('username'));
    }
    public function getcurrentemailAction(Request $req)
    {
        //return new JsonResponse($req->getSession()->get('email'));
    }
}