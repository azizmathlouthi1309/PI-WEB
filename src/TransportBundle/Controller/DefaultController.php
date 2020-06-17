<?php

namespace TransportBundle\Controller;

use ClassesWithParents\D;
use ClassBundle\Entity\Classe;
use Doctrine\ORM\Mapping\Entity;
use EspaceParentBundle\Entity\Child;
use FirstBundle\FirstBundle;
use FirstBundle\Repository\ClasseRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Form;
use TransportBundle\Entity\Driver;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use TransportBundle\Entity\Traffectation;
use TransportBundle\Entity\Vehicule;
use Twilio\Rest\Client;
use Dompdf\Dompdf;
use Dompdf\Options;

class DefaultController extends Controller
{
    public function indexAction(Request $request)
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        if ($this->isGranted('ROLE_ADMIN') == true) {
            $em = $this->getDoctrine()->getManager();
            $vehicules = $em->getRepository(Vehicule::class)->findAll();
            $affectations = $em->getRepository(Traffectation::class)->findAll();
            $drivers = $em->getRepository(Driver::class)->findAll();
            $nb_veh = 0;
            $nb_driv = 0;
            foreach ($vehicules as $veh) {
                if ($veh->getStatus() == 0) {
                    $nb_veh++;
                }
            }
            foreach ($drivers as $driver) {
                foreach ($affectations as $affect) {
                    if ($affect->getDriver()->getId() == $driver->getId()) {
                        $nb_driv++;
                    }
                }
            }

            $this->get('session')->getFlashBag()->add(
                'notice',
                $nb_veh . '  (Vehicule/s) are/is free'
            );
            $this->get('session')->getFlashBag()->add(
                'notice',
                count($drivers) - $nb_driv . '  (Driver/s) are/is free'
            );
            return $this->render('default/transport/indexa.html.twig');


        }

        return $this->render('default/transport/index.html.twig');


    }

    public function transportAction()
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();


        if ($this->isGranted('ROLE_USER') == false) {
            return $this->redirectToRoute('fos_user_security_login');
        } else {

            $em = $this->getDoctrine()->getManager();
            $vehicules = $em->getRepository(Traffectation::class)->findAll();
            return $this->render('default/transport/transport.html.twig', array("user" => $user, "vehicules" => $vehicules));
        }

    }

    public function addAction(Request $request)
    {
        $driver = new Driver();
        $form = $this->createFormBuilder($driver)
            ->add('firstname', TextType::class)
            ->add('lastname', TextType::class)
            ->add('adress', TextType::class)
            ->add('age', IntegerType::class)
            ->add('Phone_Number', IntegerType::class)
            ->add('Add', SubmitType::class, ['label' => 'Add Driver'])
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($driver);
            $em->flush();
            return $this->redirectToRoute('transport_back_driver');
        }
        return $this->render('default/transport/admin_crud_menu_transport.html.twig', ['f' => $form->createView()]);
    }

    public function showdriversAction()
    {
        $drivers = $this->getDoctrine()->getManager()->getRepository(Driver::class)->findAll();
        return $this->render('default/transport/listdrivers.html.twig', ['drivers' => $drivers]);

    }

    public function deletedriversAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $driver = $this->getDoctrine()->getRepository(Driver::class)->find($id);
        $em->remove($driver);
        $em->flush();
        return $this->redirectToRoute('transport_back_driver');
    }

    public function showformeditAction(Request $request, $id)
    {
        $driver = new Driver();
        $em = $this->getDoctrine()->getManager();
        $driver = $em->getRepository(Driver::class)->find($id);
        $drivers = $em->getRepository(Driver::class)->findAll();
        $form = $this->createFormBuilder($driver)
            ->add('firstname', TextType::class)
            ->add('lastname', TextType::class)
            ->add('adress', TextType::class)
            ->add('age', IntegerType::class)
            ->add('Phone_Number', IntegerType::class)
            ->add('Add', SubmitType::class, ['label' => 'Confirm Edit'])
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em->flush();
            return $this->render('default/transport/listdrivers.html.twig', array('drivers' => $drivers));
        }
        return $this->render('default/transport/edit_driver.html.twig', ['f' => $form->createView()]);

    }

    public function vehiculeshomeAction(Request $request)
    {
        $vehicule = new Vehicule();
        $form = $this->createFormBuilder($vehicule)
            ->add('brand', TextType::class)
            ->add('type', ChoiceType::class, [
                'choices' => [
                    'Diesel' => 'Diesel',
                    'Petrol' => 'Petrol'
                ]])
            ->add('capacity', IntegerType::class)
            ->add('Add', SubmitType::class, ['label' => 'Add Vehicule'])
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
            //return new Response($data->getDriver()->getName());
            $em = $this->getDoctrine()->getManager();

            $existingvehicules = $em->getRepository(Vehicule::class)->findAll();
            $vehicule->setStatus(0);
            $em->persist($vehicule);
            $em->flush();
            return $this->redirectToRoute('transport_back_vehicule_homepage_show');
            //return $this->redirectToRoute('transport_back_driver');
        }
        return $this->render('default/transport/vehicules_add.html.twig', ['f' => $form->createView()]);
    }

    public function vehiculesshowAction()
    {
        $em = $this->getDoctrine()->getManager();
        $vehicules = $em->getRepository(Vehicule::class)->findAll();

        return $this->render('default/transport/listvehicules.html.twig', ['vehicules' => $vehicules]);
    }

    public function showformeditvAction(Request $request, $id)
    {
        $vehicule = new Vehicule();
        $em = $this->getDoctrine()->getManager();
        $vehicule = $em->getRepository(Vehicule::class)->find($id);
        $form = $this->createFormBuilder($vehicule)
            ->add('brand', TextType::class)
            ->add('type', ChoiceType::class, [
                'choices' => [
                    'Diesel' => 'Diesel',
                    'Petrol' => 'Petrol'
                ]])
            ->add('capacity', IntegerType::class)
            ->add('Add', SubmitType::class, ['label' => 'Confirm Edit'])
            ->getForm();

        $em = $this->getDoctrine()->getManager();
        $vehicules = $em->getRepository(Vehicule::class)->findAll();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em->flush();
            return $this->render('default/transport/listvehicules.html.twig', array('vehicules' => $vehicules));
        }
        return $this->render('default/transport/editvehicule.html.twig', ['f' => $form->createView()]);
    }

    public function deletevehiculesAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $driver = $this->getDoctrine()->getRepository(Vehicule::class)->find($id);
        $em->remove($driver);
        $em->flush();
        return $this->redirectToRoute('transport_back_vehicule_homepage_show');
    }

    public function smsformAction()
    {
        return $this->render('default/transport/smsform.html.twig');
    }

    public function smssendAction(Request $request)
    {
        // Find your Account Sid and Auth Token at twilio.com/console
        $tel = $request->get('number');
        $sid = "AC6d836bce0a77691fe7146f351048e8f2";
        $token = "ea6ad83377ca7411da97e340d28c3bb1";
        $twilio = new Client($sid, $token);
        $message = $twilio->messages
            ->create($tel, // to
                [
                    "body" => "Hi sir your child is in class :LIONS",
                    "from" => "+12029331218"
                ]
            );
        return $this->render('default/transport/index.html.twig');
    }

    public function pdfAction()
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('isRemoteEnabled', TRUE);
        //$pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $em = $this->getDoctrine()->getManager();
        $vehicules = $em->getRepository(Vehicule::class)->findAll();

        // Retrieve the HTML generated in our twig file
        //return $this->render('default/listvehicules.html.twig',['vehicules'=>$vehicules]);
        $html = $this->renderView('default/transport/listpdf.html.twig', [
            'vehicules' => $vehicules,

        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }

    public function searchresultAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $vehicules = $em->getRepository(Vehicule::class)->findAll();
        if ($request->isMethod('POST')) {
            $brand = $request->get('brand');

            $resultvehicules = array();
            foreach ($vehicules as $veh) {
                $i = 0;
                if (strpos($veh->getBrand(), $brand) === 0) {
                    $resultvehicules[$i] = $veh;
                    $i++;
                }
            }
        }
        return $this->render('default/transport/listvehicules.html.twig', array('vehicules' => $resultvehicules));
    }

    public function affectAction()
    {
        $em = $this->getDoctrine()->getManager();
        $affectations = $em->getRepository(Traffectation::class)->findAll();
        return $this->render('default/transport/listaffect.html.twig', array('affectations' => $affectations));
    }

    public function addaffectationAction(Request $request)
    {

        $affectation = new Traffectation();
        $form = $this->createFormBuilder($affectation)
            ->add('driver', EntityType::class, array('class' => 'TransportBundle:Driver',
                'choice_label' => 'firstname',
                'multiple' => false))
            ->add('vehicule', EntityType::class, array('class' => 'TransportBundle:Vehicule',
                'choice_label' => 'brand',
                'multiple' => false))
            ->add('grade', EntityType::class, array('class' => 'ClassBundle:Classe',
                'choice_label' => 'name',
                'multiple' => false))
            ->add('Add', SubmitType::class, ['label' => 'Confirm Affectation'])
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $affectations = $em->getRepository(Traffectation::class)->findAll();

            if ($form->getData()->getGrade()->getNbchild() > $form->getData()->getVehicule()->getCapacity()) {
                $this->get('session')->getFlashBag()->add(
                    'notice',
                    'Vehicule cant support the number of children'
                );
                return $this->redirectToRoute('transport_back_affect_add');
            }
            foreach ($affectations as $aff) {
                if ($form->getData()->getDriver()->getId() == $aff->getDriver()->getId()) {
                    $this->get('session')->getFlashBag()->add(
                        'notice',
                        'Driver is already affected '
                    );
                    return $this->redirectToRoute('transport_back_affect_add');
                } else if ($form->getData()->getVehicule()->getStatus() == 1) {
                    $this->get('session')->getFlashBag()->add(
                        'notice',
                        'Vehicule is already affected '
                    );
                    return $this->redirectToRoute('transport_back_affect_add');
                } else if ($form->getData()->getGrade()->getId() == $aff->getGrade()->getId()) {
                    $this->get('session')->getFlashBag()->add(
                        'notice',
                        'Class already affected'
                    );
                    return $this->redirectToRoute('transport_back_affect_add');
                }

            }
            //return new Response($form->getData()->getDriver()->getFirstname());

            $form->getData()->getVehicule()->setStatus(1);
            $em->persist($affectation);
            $em->flush();
            $affectations = $em->getRepository(Traffectation::class)->findAll();
            return $this->redirectToRoute('transport_back_affect', array('affectations' => $affectations));
        }
        return $this->render('default/transport/addaffectation.html.twig', ['f' => $form->createView()]);
    }

    public function deleteaffectAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $driver = $this->getDoctrine()->getRepository(Traffectation::class)->find($id);
        $driver->getVehicule()->setStatus(0);
        $em->remove($driver);
        $em->flush();
        return $this->redirectToRoute('transport_back_affect');
    }

    public function editaffectAction(Request $request, $id)
    {
        $affectation = new Traffectation();
        $em = $this->getDoctrine()->getManager();
        $affectation = $em->getRepository(Traffectation::class)->find($id);
        $affectations = $em->getRepository(Traffectation::class)->findAll();
        $form = $this->createFormBuilder($affectation)
            ->add('driver', EntityType::class, array('class' => 'TransportBundle:Driver',
                'choice_label' => 'firstname',
                'multiple' => false))
            ->add('vehicule', EntityType::class, array('class' => 'TransportBundle:Vehicule',
                'choice_label' => 'brand',
                'multiple' => false))
            ->add('grade', EntityType::class, array('class' => 'TransportBundle:Grade',
                'choice_label' => 'name',
                'multiple' => false))
            ->add('Add', SubmitType::class, ['label' => 'Confirm Affectation'])
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            if ($form->getData()->getGrade()->getNbchild() > $form->getData()->getVehicule()->getCapacity()) {
                $this->get('session')->getFlashBag()->add(
                    'notice',
                    'Vehicule cant support the number of children'
                );
                return $this->redirectToRoute('transport_back_affect_edit', array('id' => $id));
            }
            foreach ($affectations as $aff) {
                if ($form->getData()->getDriver()->getId() == $aff->getDriver()->getId()) {
                    $this->get('session')->getFlashBag()->add(
                        'notice',
                        'Driver is already affected '
                    );
                    return $this->redirectToRoute('transport_back_affect_edit', array('id' => $id));
                } else if ($form->getData()->getVehicule()->getStatus() == 1) {
                    $this->get('session')->getFlashBag()->add(
                        'notice',
                        'Vehicule is already affected '
                    );
                    return $this->redirectToRoute('transport_back_affect_edit', array('id' => $id));
                }
                $em->flush();
                $affectations = $em->getRepository(Traffectation::class)->findAll();

            }

            //return $this->redirectToRoute('transport_back_affect');
        }
        return $this->render('default/transport/affectedit.html.twig', array('f' => $form->createView()));
    }


    public function sendmailAction()
    {
        {
            $transport = (new \Swift_SmtpTransport('smtp.googlemail.com', 465, 'ssl'))
                ->setUsername('aziz13mth@gmail.com')
                ->setPassword('abdou15121963bab');

// Create the Mailer using your created Transport
            $mailer = new \Swift_Mailer($transport);
            $em = $this->getDoctrine()->getManager();
            $children = $em->getRepository(Child::class)->findAll();
            if (empty($children)) {
                return new Response('kindo is empty');
            } else {
                foreach ($children as $child) {
                    if ($child->getParentId() == $this->getUser()->getId()) {
                        $classname = $child->getClasse()->getName();
                        $body = 'Hello, <p>' . $this->getUser() . '<span>  Your Child is in Class ' . $classname . '</span>.</p>';

                        $message = (new \Swift_Message(('Response To Your Request')))
                            ->setFrom(['aziz13mth@gmail.com' => 'KindoGarten'])
                            ->setTo([$this->getUser()->getEmail()])
                            ->setBody($body)
                            ->setContentType('text/html');

// Send the message
                        $mailer->send($message);

                    } else {
                        $this->get('session')->getFlashBag()->add(
                            'notice',
                            'You have no child in our kindo'
                        );
                        $vehicules = $em->getRepository(Vehicule::class)->findAll();
                        $affectations = $em->getRepository(Traffectation::class)->findAll();
                        $drivers = $em->getRepository(Driver::class)->findAll();
                        return $this->render('default/transport/transport.html.twig', array("user" => $this->getUser(), "vehicules" => $affectations));
                    }
                }
                return $this->redirectToRoute('transport_homepage');
// Create a message

            }
        }

    }
}
